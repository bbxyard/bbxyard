const Base = require('./base.js');

module.exports = class extends Base {
  async indexAction() {
    return this.listAction();
  }

  async listAction() {
    const field = ['id', 'name', 'floor_price', 'app_list_pic_url'];
    const page = this.get('page') || 1;
    const size = this.get('size') || 8;
    const data = await this.model('brand').field(field).page(page, size).countSelect();
    return this.success({brand: data});
  }

  async detailAction() {
    const id = this.get('id');
    const data = await this.model('brand').where({id: id}).find();
    return this.success({brand: data});
  }
};
