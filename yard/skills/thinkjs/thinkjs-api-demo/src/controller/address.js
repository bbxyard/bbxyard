const Base = require('./base.js');

module.exports = class extends Base {
  async indexAction() {
    return this.listAction();
  }

  /**
   * 获取用户的收货地址
   * @return {Promise} []
   */
  async listAction() {
    const addressList = await this.model('address').where({user_id: think.userId}).select();
    for (const item of addressList) {
      this.addressId2Name(item, item);
    }
    return this.success({addressList: addressList});
  }

  /**
   * 获取收货地址的详情
   * @return {Promise} []
   */
  async detailAction() {
    const userId = think.userId;
    const addressId = this.get('id');
    const addressInfo = await this.model('address').where({user_id: userId, id: addressId}).find();
    if (!think.isEmpty(addressInfo)) {
      this.addressId2Name(addressInfo, addressInfo);
    }
    return this.success(addressInfo);
  }

  /**
   * 添加或更新收货地址
   * @returns {Promise.<Promise|PreventPromise|void>}
   */
  async saveAction() {
    let addressId = this.post('id');
    const userId = this.getLoginUserId();
    const addressData = {
      name: this.post('name'),
      mobile: this.post('mobile'),
      province_id: this.post('province_id'),
      city_id: this.post('city_id'),
      district_id: this.post('district_id'),
      address: this.post('address'),
      user_id: userId,
      is_default: this.post('is_default') === true ? 1 : 0
    };

    if (think.isEmpty(addressId)) {
      addressId = await this.model('address').add(addressData);
    } else {
      await this.model('address').where({id: addressId, user_id: userId}).update(addressData);
    }

    // 如果设置为默认，则取消其它的默认
    if (this.post('is_defalut')) {
      await this.model('address').where({id: ['<>', addressId], user_id: userId}).update({is_default: 0});
    }
    const addressInfo = await this.model('address').where({id: addressId}).find();
    return this.success(addressInfo);
  }

  async addressId2Name(inAddressInfo, outAddressInfo) {
    const regionModel = this.model('region');
    outAddressInfo.province_name = await regionModel.getRegionName(inAddressInfo.province_id);
    outAddressInfo.city_name = await regionModel.getRegionName(inAddressInfo.city_id);
    outAddressInfo.district_name = await regionModel.getRegionName(inAddressInfo.district_id);
    outAddressInfo.full_region = outAddressInfo.province_name + outAddressInfo.city_name + outAddressInfo.district_name;
    return outAddressInfo;
  }

  /**
   * 删除指定的收货地址
   * @returns {Promise.<Promise|PreventPromise|void>}
   */
  async deleteAction() {
    const userId = think.userId;
    const addressId = this.post('id');
    await this.model('address').where({id: addressId, user_id: userId}).delete();
    return this.success('删除成功');
  }
};
