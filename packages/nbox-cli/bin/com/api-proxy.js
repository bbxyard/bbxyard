/**
 * @file api-proxy.js
 * @description 接口代理
 */

const nbox = require(process.env.NODE_ENV === 'dev' ? '../../../nbox/index' : 'nbox');

module.exports = nbox;
