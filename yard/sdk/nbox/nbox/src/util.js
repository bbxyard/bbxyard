/**
 * @name 全局对外工具接口
 * @description 3rd基础库
 */

// 公共工具集
const misc = require('./com/misc'); // 杂项
const strfmt = require('./com/strfmt'); // 字符串格式化
const log = require('./com/logger'); // 日志
const val = require('./com/validator'); // 检验器

// 微信相关
const wxapp = require('./wxapp/misc');

/**
 * 快捷私有接口
 * @param {*} args 
 */
function debugLog(...args) {
  console.log(...args);
}
function traceLog(...args) {
  console.log(...args);
}

const prv = {
  debugLog,
  traceLog
};

/**
 * @name 对外接口
 * @type {Object}
 */
const UT = Object.assign(
  {},

  // 基础库
  misc,
  strfmt,
  log,
  val,

  // 应用类
  wxapp,

  // 私有可重写区
  prv
);

module.exports = UT;
