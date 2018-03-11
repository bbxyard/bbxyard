/**
 * IO-DB封装操作
 * @type {[type]}
 */
module.exports = class extends think.Service {
  /**
   * store = add / update
   * @param  {[type]}  tabname [description]
   * @param  {[type]}  id      [description]
   * @param  {[type]}  values  [description]
   * @param  {[type]}  that    [description]
   * @return {Promise}         [description]
   */
  async storeById(tabname, id, values, that) {
    const model = that.model(tabname);
    if (id > 0) {
      await model.where({id: id}).update(values);
    } else {
      delete values.id;
      await model.add(values);
    }
    return that.success(values);
  }

  async deleteById(tabname, id, that) {
    await this.model(tabname).where({id: id}).limit(1).delete();
    return this.success();
  }

  async deleteByObj(tabname, obj, that) {
    await this.model(tabname).where(obj).limit(1).delete();
    return this.success();
  }

  /**
   * showInfo
   * @param  {[type]}  tabname   [description]
   * @param  {[type]}  queryCond [description]
   * @param  {[type]}  fieldCond [description]
   * @param  {[type]}  that      [description]
   * @return {Promise}           [description]
   */
  async showInfo(tabname, queryCond, fieldCond, that) {
    if (think.isEmpty(fieldCond)) {
      fieldCond = ['*'];
    }
    const data = await that.model(tabname).field(fieldCond).where(queryCond).find();
    return that.success(data);
  }
};
