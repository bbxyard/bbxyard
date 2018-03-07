const Base = require('./base.js');

module.exports = class extends Base {
  indexAction() {
    this.body = 'OK';
  }

  getOutField() {
    return ['id', 'title', 'price_info', 'scene_pic_url', 'subtitle'];
  }

  async listAction() {
    const field = this.getOutField();
    const model = this.model('topic');
    const page = this.get('page') || 1;
    const size = this.get('size') || 10;
    const data = await model.field(field).page(page, size).countSelect();
    return this.success(data);
  }

  async detailAction() {
    const data = await this.model('topic').where({id: this.get('id')}).find();
    return this.success(data);
  }

  async relatedAction() {
    const data = await this.model('topic').field(this.getOutField()).limit(4).select();
    return this.success(data);
  }
};
