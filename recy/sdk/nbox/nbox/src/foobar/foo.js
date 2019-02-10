/**
 * @file foo.js
 * @description 接口导出规范样品文件 - foo
 */
const SUB_FOOBAR_FOO = (function() {

  function add(x, y) {
    return x + y;
  }

  function mult(x, y) {
    return x * y;
  }

  const foo = {
    addF: add,
    multF: mult
  };

  return foo;

})();
