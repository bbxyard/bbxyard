module.exports = class extends think.Controller {
  async __before() {
    // 根据token值获取用户id
    this.token = this.ctx.header['x-itshop-token'] || '';
    const tokenService = think.service('token');
    think.userId = await tokenService.getUserId();

    const publicController = this.config('publicController');
    const publicAction = this.config('publicAction');
    // If not public, then verify login
    const controllerAction = this.ctx.controller + '/' + this.ctx.action;
    if (!publicController.includes(this.ctx.controller) && !publicAction.includes(controllerAction)) {
      if (think.userId <= 0) {
        // return this.fail(401, 'please login 1st');
      }
    }
  }

  /**
   * 显示请求信息
   * @return {[type]} [description]
   */
  showRequest() {
    console.log('this.ctx: ', this.ctx);
    console.log(`this.ctx.header: ${this.ctx.header}`);
  }

  /**
   * 获取时间戳
   * @return {Number}
   */
  getTime() {
    return parseInt(Date.now() / 1000);
  }

  /**
   * 获取当前登录用户的id
   * @return {*}
   */
  getLoginUserId() {
    return think.userId;
  }
};
