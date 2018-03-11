const Base = require('../base.js');

module.exports = class extends Base {
  constructor(ctx) {
    super(ctx);
    this.tabname = 'category';
  }

  /**
   * index
   * @return {Promise} [description]
   */
  async indexAction() {
    const model = this.model(this.tabname);
    const data = await model.where({is_show: 1}).order(['sort_order ASC']).select();
    const topCategory = data.filter((item) => { return item.parent_id === 0 });
    const categoryList = [];
    topCategory.map((item) => {
      item.level = 1;
      categoryList.push(item);
      data.map((child) => {
        if (child.parent_id === item.id) {
          child.level = 2;
          categoryList.push(child);
        }
      });
    });
    return this.success(categoryList);
  }

  async topCategoryAction() {
    const model = this.model(this.tabname);
    const data = model.where({parent_id: 0}).order(['id ASC']).select();
    return this.success(data);
  }

  /**
   * info
   * @return {Promise} [description]
   */
  async infoAction() {
    return this.db.showInfo(this.tabname, {id: this.get('id')}, null, this);
  }

  /**
   * store
   * @return {Promise} [description]
   */
  async storeAction() {
    if (!this.post) {
      return false;
    }
    const id = this.post('id');
    const values = this.post();
    values.is_show = values.is_show ? 1 : 0;

    const db = this.getDB();
    return db.storeById(this.tabname, id, values, this);
  }

  async destoryAction() {
    const id = this.post('id');
    await this.model('category').where({id: id}).limit(1).delete();
    // todo del pics
    return this.success();
  }
};
