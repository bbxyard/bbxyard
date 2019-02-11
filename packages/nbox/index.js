/**
 * @file index.js(nbox.js)
 * @description 模块大集合
 */

module.exports = {
  res: require('./lib/nbox-res'),
  kit: require('./lib/nbox-kit'),
  core: require('./lib/nbox-core'),
  wepy: require('./lib/nbox-wepy'),
  help: function() { console.log('nbox: ', module.exports); }
};
