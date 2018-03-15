const Base = require('./base.js');

module.exports = class extends Base {
  async indexAction() {
    const banner = await this.model('ad').where({ad_position_id: 1}).select();
    const channel = await this.model('channel').order({sort_order: 'asc'}).select();
    const newGoodsList = await this.model('goods').field(['id', 'name', 'list_pic_url', 'retail_price']).where({is_new: 1}).limit(4).select();
    const hotGoodsList = await this.model('goods').field(['id', 'name', 'list_pic_url', 'retail_price', 'goods_brief']).where({is_hot: 1}).limit(3).select();
    const brandList = await this.model('brand').where({is_new: 1}).order({new_sort_order: 'asc'}).limit(4).select();
    const topicList = await this.model('topic').limit(3).select();

    const categoryList = await this.model('category').where({parent_id: 0, name: ['<>', '推荐']}).select();
    const newCategoryList = [];
    for (const item of categoryList) {
      const childCatIds = await this.model('category').where({parent_id: item.id}).getField('id', 100);
      const catGoods = await this.model('goods').field(['id', 'name', 'list_pic_url', 'retail_price']).where({category_id: ['IN', childCatIds]}).limit(7).select();
      newCategoryList.push({
        id: item.id,
        name: item.name,
        goodsList: catGoods
      });
    }

    return this.success({
      banner: banner,
      channel: channel,
      newGoodsList: newGoodsList,
      hotGoodsList: hotGoodsList,
      brandList: brandList,
      topicList: topicList,
      categoryList: newCategoryList
    });
  }
};
