const Base = require('../base.js');

module.exports = class extends Base {
  async indexAction() {
    const page = this.get('page') || 1;
    const size = this.get('size') || 10;
    const orderSn = this.get('orderSn') || '';
    const consignee = this.get('consignee') || '';
    const model = this.model('order');
    const data = await model.where({order_sn: ['like', `%${orderSn}%`], consignee: ['like', `%${consignee}%`]})
      .order(['id DESC']).page(page, size).countSelect();
    const newList = [];
    for (const item of data.data) {
      item.order_status_text = await this.model('order').getOrderStatusText(item.id);
      newList.push(item);
    }
    data.data = newList;
    return this.success(data);
  }

  async infoAction() {
    const id = this.get('id');
    const res = this.db.showInfo(this.tabname, {id: id}, null, this);
    return res;
  }

  async storeAction() {
    if (!this.isPost) {
      return false;
    }

    const id = this.post('id');
    const values = this.post();
    values.is_show = values.is_show ? 1 : 0;
    values.is_new = values.is_new ? 1 : 0;
    return this.db.storeById(this.tabname, id, values);
  }

  async destroyAction() {
    const id = this.post('id');
    this.db.deleteById(this.tabname, id, this);

    // 删除订单商品
    this.db.deleteByObj('order_goods', {order_id: id}, this);

    // TODO 事务，验证订单是否可删除（只有失效的订单才可以删除）
    return this.success();
  }
};
