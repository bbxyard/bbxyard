module.exports = class extends think.Model {
  /**
   * 获取商品的product
   * @param  {[type]}  goodsId [description]
   * @return {Promise}         [description]
   */
  async getProductList(goodsId, field = ['*']) {
    const goods = await this.model('product').field(field).where({goods_id: goodsId}).select();
    return goods;
  }

  /**
   * 获取商品的规格信息
   * 根据sku商品信息，查找规格值列表
   * @param  {[type]}  goodsId [description]
   * @return {Promise}         [description]
   */
  async getSpecList(goodsId) {
    const specRes = await this.model('goods_specification').alias('gs')
      .field(['gs.*', 's.name'])
      .join({ table: 'specification', join: 'inner', as: 's', on: ['specification_id', 'id'] })
      .where({goods_id: goodsId})
      .select();
    const specList = [];
    const hasSpecList = {};
    // 按规格名称分组
    for (let i = 0; i < specRes.length; ++i) {
      const specItem = specRes[i];
      if (!hasSpecList[specItem.specification_id]) {
        specList.push({
          specification_id: specItem.specification_id,
          name: specItem.name,
          valueList: [specItem]
        });
        hasSpecList[specItem.specification_id] = specItem;
      } else {
        for (let j = 0; j < specList.length; ++j) {
          if (specList[j].specification_id === specItem.specification_id) {
            specList[j].valueList.push(specItem);
            break;
          }
        }
      }
    }
    return specList;
  }
};
