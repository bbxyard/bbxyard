var api = require('../config.js').api;

/**
 * [formatTime description]
 * @param  {[type]} date [description]
 * @return {[type]}      [description]
 */
function formatTime(date) {
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();

  var hour = date.getHours();
  var minute = date.getMinutes();
  var second = date.getSeconds();

  return [year, month, day].map(formatNumber).join('-') + ' ' +
    [hour, minute, second].map(formatNumber).join(':');
}

/**
 * [formatNumber description]
 * @param  {[type]} n [description]
 * @return {[type]}   [description]
 */
function formatNumber(n) {
  n = n.toString();
  return n[1] ? n : '0' + n;
}

/**
 * 封封微信的的request
 * @param  {[type]} url            [description]
 * @param  {Object} [data={}]      [description]
 * @param  {String} [method="GET"] [description]
 * @return {[type]}                [description]
 */
function request(url, data = {}, method = "GET") {
  return new Promise(function (resolve, reject) {
    wx.request({
      url: url,
      data: data,
      method: method,
      header: {
        'Content-Type': 'application/json',
        'X-xshop-Token': wx.getStorageSync('token')
      },
      success: function (res) {
        console.log("success");

        if (res.statusCode === 200) {

          if (res.data.errno === 401) {
            // 需要登录后才可以操作

            let code = null;
            return login().then((res) => {
              code = res.code;
              return getUserInfo();
            }).then((userInfo) => {
              // 登录远程服务器
              request(api.AuthLoginByWeixin, { code: code, userInfo: userInfo }, 'POST').then(res => {
                if (res.errno === 0) {
                  // 存储用户信息
                  wx.setStorageSync('userInfo', res.data.userInfo);
                  wx.setStorageSync('token', res.data.token);
                  resolve(res);
                } else {
                  reject(res);
                }
              }).catch((err) => {
                reject(err);
              });
            }).catch((err) => {
              reject(err);
            })
          } else {
            resolve(res.data);
          }
        } else {
          reject(res.errMsg);
        };

      },
      fail: function (err) {
        reject(err);
        console.log('failed');
      }
    });
  });
}

/**
 * 检查微信会话是否过期
 * @return {[type]} [description]
 */
function checkSession() {
  return new Promise(function (resolve, reject) {
    wx.checkSession({
      success: () => { resolve(true); },
      fail: () => { reject(false); }
    });
  });
}

/**
 * 调用微信登录
 * @return {[type]} [description]
 */
function login() {
  return new Promise(function (resolve, reject) {
    wx.login({
      success: function (res) {
        if (res.code) {
          // 登录远程服务器
          console.log(res);
          resolve(res);
        } else {
          reject(res);
        }
      },
      fail: function (err) {
        reject(err);
      }
    });
  });
}

/**
 * UserInfo
 * @return {[type]} [description]
 */
function getUserInfo() {
  return new Promise(function (resolve, reject) {
    wx.getUserInfo({
      withCredentials: true,
      success: (res) => {
        console.log(res);
        resolve(res);
      },
      fail: (err) => { reject(err); }
    });
  });
}

/**
 * redirect Url
 * @param  {[type]} url [description]
 * @return {[type]}     [description]
 */
function redirect(url) {
  // 判断页面是否需要登录
  const needLogin = false;
  if (needLogin) {
    wx.redirectTo({
      url: '/pages/auth/login/login'
    });
    return false;
  } else {
    wx.redirectTo({url: url});
  }
}

/**
 * [showErrorToast description]
 * @param  {[type]} msg [description]
 * @return {[type]}     [description]
 */
function showErrorToast(msg) {
  wx.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  });
}

/**
 * 对外接口
 * @type {Object}
 */
module.exports = {
  formatTime,
  request,
  redirect,
  showErrorToast,
  checkSession,
  login,
  getUserInfo
};
