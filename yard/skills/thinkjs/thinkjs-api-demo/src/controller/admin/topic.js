const Base = require('../base.js');

module.exports = class extends Base {
  constructor(ctx) {
    super(ctx);
    this.tabname = 'order';
  }

  async indexAction() {
    const page = this.get('page') || 1;
    const size = this.get('size') || 10;
    const name = this.get('name') || '';
    const model = this.model(this.tabname);
    const data = await model.where({title: ['like', `%${name}%`]})
      .order(['id DESC']).page(page, size).countSelect();
    return this.success(data);
  }

  async infoAction() {
    const id = this.get('id');
    return this.db.showInfo(this.tabname, {id: id}, null, this);
  }

  async storeAction() {
    if (!this.isPost) {
      return false;
    }

    const id = this.post('id');
    const values = this.post();
    values.is_show = values.is_show ? 1 : 0;
    values.is_new = values.is_new ? 1 : 0;

    const res = await this.db.storeById(this.tabname, id, values, this);
    return res;
  }

  async destroyAction() {
    const id = this.post('id');
    const res = await this.db.deleteById(this.tabname, id, this);
    // todo del pics
    return res;
  }
};
