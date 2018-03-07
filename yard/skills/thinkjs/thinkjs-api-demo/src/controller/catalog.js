const Base = require('./base.js');

module.exports = class extends Base {
  getField() {
    return ['id', 'parent_id', 'name', 'front_name'];
  }

  async indexAction() {
    const catId = this.get('id');
    const model = this.model('category');
    const data = await model.limit(10).field(this.getField()).where({parent_id: 0}).select();

    let currentCategory = null;
    if (catId) {
      currentCategory = await model.where({id: catId}).find();
    }

    if (think.isEmpty(currentCategory)) {
      currentCategory = data[0];
    }
    // 获取子分类数据
    if (currentCategory && currentCategory.id) {
      currentCategory.subCategoryList = await model
        .field(this.getField())
        .where({parent_id: currentCategory.id})
        .select();
    }

    return this.success({
      categoryList: data,
      currentCategory: currentCategory
    });
  }

  async currentAction() {
    const catId = this.get('id');
    const model = this.model('category');

    let currentCategory = null;
    if (catId) {
      currentCategory = await model.where({'id': catId}).find();
    }
    // 获取子分类数据
    if (currentCategory && currentCategory.id) {
      currentCategory.subCategoryList = await model.field(this.getField()).where({'parent_id': currentCategory.id}).select();
    }

    return this.success({
      currentCategory: currentCategory
    });
  }
};
