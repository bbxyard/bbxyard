const Base = require('./base.js');

module.exports = class extends Base {
  /**
   * 列出所有货物
   * @return {Promise} [description]
   */
  async indexAction() {
    const model = this.model('goods');
    const goodsList = await model.select();
    return this.success(goodsList);
  }


  /**
   * [skuAction description]
   * @return {Promise} [description]
   */
  async skuAction() {
    const goodsId = this.get('id');
    const model = this.model('goods');
    return this.success({
      productList: await model.getProductList(goodsId, ['id', 'goods_id', 'goods_number'])
    });
  }


  /**
   * [categoryAction description]
   * @return {Promise} [description]
   */
  async categoryAction() {
    const field = ['id', 'parent_id', 'name'];
    const model = this.model('category');
    const curCat = await model.field(field).where({id: this.get('id')}).find();
    const parent = await model.field(field).where({id: curCat.parent_id}).find();
    const broCat = await model.field(field).limit(5).where({parent_id: curCat.parent_id}).select();
    return this.success({
      curCat: curCat,
      parent: parent,
      broCat: broCat
    });
  }

  /**
   * 获取商品列表
   * @return {Promise} [description]
   */
  async listAction() {
    const catId = this.get('catId');
    const brandId = this.get('brandId');
    const keyword = this.get('keyword');
    const isNew = this.get('isNew');
    const isHot = this.get('isHot');
    const page = this.get('page');
    const size = this.get('size');
    const sort = this.get('sort');
    const order = this.get('order');

    const goodsQuery = this.model('goods');
    const catQuery = this.model('category');
    const whereCond = {};
    if (!think.isEmpty(isNew)) whereCond.is_new = isNew;
    if (!think.isEmpty(isHot)) whereCond.is_hot = isHot;
    if (!think.isEmpty(keyword)) {
      whereCond.name = ['like', `%${keyword}%`];
      // 添加到搜索历史
      await this.model('search_history').add({
        keyword: keyword,
        user_id: think.userId,
        add_time: parseInt(new Date().getTime() / 1000)
      });
    }
    if (!think.isEmpty(brandId)) whereCond.brand_id = brandId;

    // 排序
    let orderCond = {};
    if (sort === 'price') {
      orderCond = { retail_price: order }; // 按价格
    } else {
      orderCond = { id: 'desc' }; // 按商品添加时间
    }

    // 筛选的分类
    let filterCat = [{ id: 0, name: '全部', checked: false }];

    const catIds = await goodsQuery.where(whereCond).getField('category_id', 10000);
    if (!think.isEmpty(catIds)) {
      // 查找二级分类的parent_id
      const parentIds = await catQuery.where({id: {'in': catIds}}).getField('parent_id', 10000);
      // 一级分类
      const parentCat = await catQuery.field(['id', 'name'])
        .order({'sort_order': 'asc'})
        .where({'id': {'in': parentIds}})
        .select();
      if (!think.isEmpty(parentCat)) {
        filterCat = filterCat.concat(parentCat);
      }
    }

    // 加入分类条件
    if (!think.isEmpty(catId) && parseInt(catId) > 0) {
      whereCond.category_id = ['in', await catQuery.getCategoryWhereIn(catId)];
    }
    // 搜索到的商品
    const goodsData = await goodsQuery.where(whereCond)
      .field(['id', 'name', 'list_pic_url', 'retail_price'])
      .order(orderCond)
      .page(page, size)
      .countSelect();
    goodsData.filterCat = filterCat.map( (v) => {
      v.checked = (think.isEmpty(catId) && v.id === 0) || (v.id === parseInt(catid));
      return v;
    });
    goodsData.goodsList = goodsData.data;
    return this.success(goodsData);
  }

  async filterAction() {
    const catId = this.get('catId');
    const keyword = this.get('keyword');
    const isNew = this.get('isNew');
    const isHot = this.get('isHot');
    const goodsQuery = this.model('goods');
    const catQuery = this.model('category');
    if (!think.isEmpty(catId)) {
      goodsQuery.where({category_id: {'in': await think.model('category').getChildCategoryId(catId)}});
    }
    if (!think.isEmpty(isNew)) {
      goodsQuery.where({is_new: isNew});
    }
    if (!think.isEmpty(isHot)) {
      goodsQuery.where({is_hot: isHot});
    }
    if (!think.isEmpty(keyword)) {
      goodsQuery.where({name: {'like': `%${keyword}%`}});
    }
    let filterCategory = [{
      id: 0,
      name: '全部'
    }];

    // 二级分类id
    const catIds = await goodsQuery.getField('category_id', 10000);
    if (!think.isEmpty(catIds)) {
      // 查找二级分类的parent_id
      const parentIds = await catQuery.where({id: {'in': catIds}}).getField('parent_id', 10000);
      // 一级分类
      const parentCat = await catQuery.field(['id', 'name']).order({'sort_order': 'asc'})
        .where({'id': {'in': parentIds}}).select();
      if (!think.isEmpty(parentCat)) {
        filterCategory = filterCategory.concat(parentCat);
      }
    }

    return this.success(filterCategory);
  }

  /**
   * 新品首发
   * @return {Promise} [description]
   */
  async newAction() {
    return this.success({
      bannerInfo: {
        url: '',
        name: '坚持初心，为你寻觅世间好物',
        img_url: 'http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png'
      }
    });
  }

  /**
   * 人气推荐
   * @return {Promise} [description]
   */
  async hotAction() {
    return this.success({
      bannerInfo: {
        url: '',
        name: '大家都在买的严选好物',
        img_url: 'http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png'
      }
    });
  }

  /**
   * 商品详情页的大家都在看的商品
   * @return {Promise} [description]
   */
  async relatedAction() {
    const goodsModel = this.model('goods');
    const relModel = this.model('related_goods');
    const goodsId = this.get('id');
    const relatedGoodsIds = await relModel.where({goods_id: goodsId}).getField('related_goods_id');
    let relatedGoods = null;
    if (think.isEmpty(relatedGoodsIds)) {
      const goodsCat = await goodsModel.where({id: goodsId}).find();
      relatedGoods = await goodsModel.where({category_id: goodsCat.category_id})
        .field(['id', 'name', 'list_pic_url', 'retail_price'])
        .limit(8)
        .select();
    } else {
      relatedGoods = await goodsModel.where({id: ['IN', relatedGoodsIds]})
        .field(['id', 'name', 'list_pic_url', 'retail_price'])
        .select();
    }
    return this.success({
      goodsList: relatedGoods
    });
  }

  /**
   * 在售的商品总数
   * @return {Promise} [description]
   */
  async countAction() {
    const goodsCount = await this.model('goods').where({is_delete: 0, is_on_sale: 1}).count('id');
    return this.success({ goodsCount: goodsCount });
  }
};
