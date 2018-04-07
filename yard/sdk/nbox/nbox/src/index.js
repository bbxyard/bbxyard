/**
 * @file index.js
 * @description nbox 统一对外接口文件
 */

function help() {
  console.log('ALL: ', module.exports);
}

const NBOX = Object.assign(
  {},
  SUB_COM,
  SUB_WX,
  SUB_FOOBAR,
  help
);

module.exports.nbox = NBOX;
module.exports.help = help;
