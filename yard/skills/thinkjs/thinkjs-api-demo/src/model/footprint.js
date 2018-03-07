module.exports = class extends think.Model {
  /**
   * 添加用户足迹
   *  用户已经登录才可以添加到足迹
   * @param  {[type]}  userId  [description]
   * @param  {[type]}  goodsId [description]
   * @return {Promise}         [description]
   */
  async addFootprint(userId, goodsId) {
    if (userId > 0 && goodsId > 0) {
      await this.add({
        user_id: userId,
        goods_id: goodsId,
        add_time: parseInt(Date.now() / 1000)
      });
    }
  }
};
