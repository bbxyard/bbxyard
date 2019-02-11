/**
 * @file index.js
 * @description nbox-core 统一对外接口文件
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

const CORE = Object.assign(
  {},

  SUB_COM_MISC,
  SUB_COM_STRFMT,
  SUB_COM_LOGGER,
  SUB_COM_VALIDATOR,
  SUB_COM_OVERRIDE,

  SUB_FOOBAR_FOO,
  SUB_FOOBAR_BAR
);

CORE.help = () => { console.log('core: ', module.exports); };

module.exports = CORE;
