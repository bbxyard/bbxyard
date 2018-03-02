/* 
 * 用jquery封装ajax调用方法，适用于thinkjs
*/
import jQuery from 'jquery'
window.jQuery = window.jQuery || jQuery
window.$ = window.$ || jQuery
export default class Sync {
  constructor({
    warn = true,
    needError = false,
    tipFn
  } = {}) {
    this.protocol = location.protocol
    this.host = location.host
    this.prefix = `${this.protocol}//${this.host}`
    this.warn = warn
    this.needError = needError
    this.tipFn = tipFn
    this.timeout = 20000
  }

  /**

   * @param {String} path   [请求地址]
   * @param {Object} param  [附带参数]
   * @param {Object} option [ajax选项]
   */
  GET(path, param = {}, option = {}) {
    const options = Object.assign({
      url: this.prefix + path,
      type: 'GET',
      data: param,
      timeout: this.timeout,
      _t: Date.now(),
      cache: false,
    }, option)
    return $.ajax(options).then(this.done, this.fail)
  }

  /**
   * [POST 方法]
   * @param {String} path   [请求地址]
   * @param {Object} param  [附带参数]
   * @param {Object} option [ajax选项]
   */
  POST(path, param = {}, option = { contentType: 'application/json' }) {
    const param_token = Object.assign({ token: window.SD.token }, param)
    const options = Object.assign({
      url: this.prefix + path,
      type: 'POST',
      data: JSON.stringify(param_token),
      timeout: this.timeout,
    }, option)
    return $.ajax(options).then((...args) => this.done(...args), (...args) => this.fail(...args))
  }
  /**
   * [PUT 方法]
   * @param {[type]} path   [请求地址]
   * @param {Object} param  [附带参数]
   * @param {Object} option [ajax选项]
   */
  PUT(path, param = {}, option = { contentType: 'application/json' }) {
    const param_token = Object.assign({ token: window.SD.token }, param)
    const options = Object.assign({
      url: this.prefix + path,
      type: 'PUT',
      data: JSON.stringify(param_token),
      timeout: this.timeout,
    }, option)
    return $.ajax(options).then((...args) => this.done(...args), (...args) => this.fail(...args))
  }
  /**
   * [DELETE 方法]
   * @param {[type]} path   [请求地址]
   * @param {Object} param  [附带参数]
   * @param {Object} option [ajax选项]
   */
  DELETE(path, param = {}, option = { contentType: 'application/json' }) {
    const param_token = Object.assign({ token: window.SD.token }, param)
    const options = Object.assign({
      url: this.prefix + path,
      type: 'DELETE',
      data: JSON.stringify(param_token),
      timeout: this.timeout,
    }, option)
    return $.ajax(options).then(this.done, this.fail)
  }
  /**
   * [FORMDATA 方法]
   * @param {String} path   [请求地址]
   * @param {Object} param  [附带参数]
   * @param {Object} option [ajax选项]
   */
  FORMDATA(path, formdata, option = {}, type = 'POST') {
    formdata.append('token', window.SD.token)
    const options = Object.assign({
      url: this.prefix + path,
      type,
      contentType: false,
      data: formdata,
      processData: false
    }, option)
    return $.ajax(options).then((...args) => this.done(...args), (...args) => this.fail(...args))
  }
  // 成功的时候的回调函数
  done(data, status, xhr) {
    return new Promise((resolve, reject) => {
      if(xhr.status === 204) resolve()
      if (data.errno !== 0) {
        if (this.warn) {
          if (data.errmsg) {
            this.tipFn && this.tipFn(data.errmsg)
          }
        }
        return reject(data)
      }
      return resolve(data.data)
    })
  }
  // 失败的时候的回调函数
  fail(xhr, errorType, error) {
    return new Promise((resolve, reject) => {
      console.error(errorType, error)
      this.tipFn && this.tipFn(error || '网络故障～～')
      throw new Error(error || '网络故障~~')
    })
  }
}
