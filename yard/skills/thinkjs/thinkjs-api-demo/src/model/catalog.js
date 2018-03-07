module.exports = class extends think.Model {
  async getChildCategoryId(parentId) {
    const childIds = await this.where({parent_id: parentId}).getField('id', 10000);
    return childIds;
  }

  async getCategoryWhereIn(catId) {
    const childIds = this.getChildCategoryId(catId);
    childIds.push(catId);
    return childIds;
  }
};
