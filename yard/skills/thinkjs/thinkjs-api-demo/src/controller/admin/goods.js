const Base = require('../base.js');

module.exports = class extends Base {
  constructor(ctx) {
    super(ctx);
    this.tabname = 'goods';
  }

  /**
   * index
   * @return {Promise} [description]
   */
  async indexAction() {
    const page = this.get('page') || 1;
    const size = this.get('size') || 10;
    const name = this.get('name') || '';
    const model = this.model(this.tabname);
    const data = await model.where({name: ['like', `%${name}%`]}).order(['id DESC']).page(page, size).countSelect();
    return this.success(data);
  }

  async infoAction() {
    const id = this.get('id');
    const res = await this.db.showInfo(this.tabname, {id: id}, null, this);
    return res;
  }

  async storeAction() {
    if (!this.post) {
      return false;
    }

    const id = this.post('id');
    const values = this.post();
    values.is_on_sale = values.is_on_sale ? 1 : 0;
    values.is_new = values.is_new ? 1 : 0;
    values.is_hot = values.is_hot ? 1 : 0;

    return this.db.store(this.tabname, id, values, this);
  }

  async destoryAction() {
    const id = this.post('id');
    const res = this.db.deleteById(this.tabname, id, this);
    // todo del pics
    return res;
  }
};
