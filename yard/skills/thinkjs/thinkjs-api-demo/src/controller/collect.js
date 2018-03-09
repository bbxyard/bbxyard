const Base = require('./base.js');

module.exports = class extends Base {
  indexAction() {
    this.body = 'OK';
  }

  async listAction() {
    const typeId = this.get('typeId');
    const list = await this.model('collect').alias('c')
      .field(['c.*', 'g.name', 'g.list_pic_url', 'g.goods_brief', 'g.retail_price'])
      .join({
        table: 'goods',
        join: 'left',
        as: 'g',
        on: ['c.value_id', 'g.id']
      }).where({user_id: think.userId, type_id: parseInt(typeId)}).countSelect();
    return this.success(list);
  }

  async addOrDelAction() {
    const typeId = this.post('typeId');
    const valueId = this.post('valueId');
    const collect = await this.model('collect').where({tpye_id: typeId, value_id: valueId, user_id: think.userId}).find();
    let collectRes = null;
    let handleType = 'add';
    if (think.isEmpty(collect)) {
      // 添加收藏
      collectRes = await this.mode('collect').add({
        type_id: typeId,
        value_id: valueId,
        user_id: think.userId,
        add_time: parseInt(new Date().getTime() / 1000)
      });
    } else {
      // 取消收藏
      collectRes = await this.model('collect').where({id: collect.id}).delete();
      handleType = 'delete';
    }

    return (collectRes > 0) ? this.success({type: handleType}) : this.fail('操作失败');
  }
};
