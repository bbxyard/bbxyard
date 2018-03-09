const Base = require('./base.js');
const rp = require('request-promise');

module.exports = class extends Base {
  async loginByWeixinAction() {
    const code = this.post('code');
    const fullUserInfo = this.post('userInfo');
    const userInfo = fullUserInfo.userInfo;
    const clientIp = '';

    // 获取openid
    const options = {
      method: 'GET',
      url: 'https://api.weixin.qq.com/sns/jscode2session',
      qs: {
        grant_type: 'authorization_code',
        js_code: code,
        secret: think.config('weixin.secret'),
        appid: think.config('weixin.appid')
      }
    };

    let sessionData = await rp(options);
    sessionData = JSON.parse(sessionData);
    if (!sessionData.openid) {
      return this.fail('登录失败');
    }

    // 验证用户信息完整性
    const crypto = require('crypto');
    const sha1 = crypto.createHash('sha1').update(fullUserInfo.rawData + sessionData.session_key).digest('hex');
    if (fullUserInfo.signature !== sha1) {
      return this.fail('登录失败');
    }

    // 解析用户数据
    const wxSvc = this.service('weixin');
    const wxUserInfo = await wxSvc.decryptUserInfoData(sessionData.session_key, fullUserInfo.encryptedData, fullUserInfo.iv);
    if (think.isEmpty(wxUserInfo)) {
      return this.fail('登录失败');
    }

    // 根据openid查找用户是否已经注册
    let userId = await this.model('user').where({weixin_openid: sessionData.openid}).getField('id', true);
    if (think.isEmpty(userId)) {
      // 注册
      userId = await this.model('user').add({
        username: 'wx' + think.uuid(6),
        password: sessionData.openid,
        register_time: this.getTime(),
        register_ip: clientIp,
        last_login_time: this.getTime(),
        last_login_ip: clientIp,
        mobile: '',
        weixin_openid: sessionData.openid,
        avatar: userInfo.avatarUrl || '',
        gender: userInfo.gender || 1,
        nickname: userInfo.nickName
      });
    }

    sessionData.user_id = userId;

    // 查询用户信息
    const field = ['id', 'username', 'nickname', 'gender', 'avatar', 'birthday'];
    const newUserInfo = await this.model('user').field(field).where({id: userId}).find();

    // 更新登录信息
    userId = await this.model('user').where({id: userId}).update({
      last_login_time: this.getTime(),
      last_login_ip: clientIp
    });

    const tknSvc = this.service('token');
    const sessionKey = await tknSvc.create(sessionData);
    if (think.isEmpty(newUserInfo) || think.isEmpty(sessionKey)) {
      return this.fail('登录失败');
    }

    return this.success({token: sessionKey, userInfo: newUserInfo});
  }

  async logoutAction() {
    return this.success();
  }
};
