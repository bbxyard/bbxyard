const Base = require('./base.js');

module.exports = class extends Base {
  /**
   * 评论类型说明：
   * 0 商品
   * 1 专题
   */
  indexAction() {
    this.body = 'OK';
  }

  /**
   * 发表评论
   * @return {Promise} [description]
   */
  async postAction() {
    const typeId = this.post('typeId');
    const valueId = this.post('valueId');
    const content = this.post('content');
    const buffer = Buffer.from(content);
    const insertId = await this.model('comment').add({
      type_id: typeId,
      value_id: valueId,
      content: buffer.toString('base64'),
      add_time: this.getTime(),
      user_id: this.getLoginUserId()
    });
    return (insertId) ? this.success('评论添加成功') : this.fail('评论保存失败');
  }

  async countAction() {
    const typeId = this.get('typeId');
    const valueId = this.get('valueId');
    const allCount = await this.model('comment').where({type_id: typeId, value_id: valueId}).count('id');
    const hasPicCount = await this.model('comment').alias('c')
      .join({
        table: 'comment_picture',
        join: 'right',
        alias: 'cp',
        on: ['c.id', 'cp.comment_id']
      })
      .where({'c.type_id': typeId, 'c.value_id': valueId})
      .count('c.id');

    return this.success({
      allCount: allCount,
      hasPicCount: hasPicCount
    });
  }

  async listAction() {
    const typeId = this.get('typeId');
    const valueId = this.get('valueId');
    const showType = this.get('showType') || 1; // 选择评论的类型 0 全部， 1 只显示图片
    const page = this.get('page') || 1;
    const size = this.get('size') || 10;
    let comments = [];
    if (showType !== 1) {
      comments = await this.model('comment').where({
        type_id: typeId,
        value_id: valueId
      }).page(page, size).countSelect();
    } else {
      comments = await this.model('comment').alias('c')
        .field(['c.*'])
        .join({
          table: 'comment_picture',
          join: 'right',
          alias: 'cp',
          on: ['c.id', 'comment_id']
        }).page(page, size)
        .where({'c.type_id': typeId, 'c.value_id': valueId})
        .countSelect();
    }

    const commentList = [];
    for (const item of comments.data) {
      const comment = {};
      comment.content = Buffer.from(item.content, 'base64').toString();
      comment.type_id = item.type_id;
      comment.value_id = item.value_id;
      comment.id = item.id;
      comment.add_time = think.datetime(new Date(item.add_time * 1000));
      comment.user_info = await this.model('user').field(['username', 'avatar', 'nickname'])
        .where({id: item.user_id}).find();
      comment.pic_list = await this.model('comment_picture')
        .where({comment_id: item.id}).select();
      commentList.push(comment);
    }
    comments.data = commentList;
    return this.success(comments);
  }
};
