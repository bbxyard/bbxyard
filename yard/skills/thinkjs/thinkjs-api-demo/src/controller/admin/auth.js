const Base = require('../base.js');

module.exports = class extends Base {
  async loginAction() {
    const username = this.post('username');
    const password = this.post('password');

    const admin = await this.model('admin').where({username: username}).find();
    if (think.isEmpty(admin)) {
      return this.fail(401, '用户名或密码不正确1');
    }

    if (think.md5(password + '' + admin.password_salt) !== admin.password) {
      return this.fail(400, '用户名或密码不正确2');
    }

    // 更新登录信息
    await this.model('admin').where({id: admin.id}).update({
      last_login_time: this.getTime(),
      last_login_ip: this.ctx.ip
    });

    const tknSvc = this.service('token');
    const sessionKey = await tknSvc.create({user_id: admin.id});

    if (think.isEmpty(sessionKey)) {
      return this.fail('登录失败');
    }

    const userInfo = {
      id: admin.id,
      username: admin.username,
      avatar: admin.avatar,
      admin_role_id: admin.admin_role_id
    };

    return this.success({token: sessionKey, userInfo: userInfo});
  }
};
