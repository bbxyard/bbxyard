module.exports = class extends think.Model {
  /**
   * 根据快递公司编码获取名称
   * @param shipperCode
   * @returns {Promise.<*>}
   */
  async getShipperNameByCode(shipperCode) {
    const name = await this.where({code: shipperCode}).getField('name', true);
    return name;
  }

  /**
   * 根据 id 获取快递公司信息
   * @param shipperId
   * @returns {Promise.<*>}
   */
  async getShipperById(shipperId) {
    const obj = await this.where({id: shipperId}).find();
    return obj;
  }
};
