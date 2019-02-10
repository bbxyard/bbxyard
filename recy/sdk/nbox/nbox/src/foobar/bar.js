/**
 * @file bar.js
 * @description 接口导出规范样品文件 - bar
 */
const SUB_FOOBAR_BAR = (function() {

  function add(x, y) {
    return x + y;
  }

  function mult(x, y) {
    return x * y;
  }

  const out = {
    add,
    mult
  };

  return out;

})();
