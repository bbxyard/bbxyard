/**
 * @file com.js
 * @description 公共小工具集
 */

/**
 * @name SUB_COM_OVERRIDE
 * @description 私有override接口
 */
const SUB_COM_OVERRIDE = {
  debugLog: function(...args) {
    console.log(...args);
  },
  traceLog: function(...args) {
    console.log(...args);
  }
};

/**
 * @name SUB_COM
 * @description COM合一
 */
const SUB_COM = Object.assign(
  {},
  SUB_COM_MISC,
  SUB_COM_STRFMT,
  SUB_COM_LOGGER,
  SUB_COM_VALIDATOR,
  SUB_COM_OVERRIDE
);

module.exports.com = SUB_COM;
