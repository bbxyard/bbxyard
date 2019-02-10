/**
 * @file webpack.config.js
 * @description 目前编译出来用不了，暂时挂起 - 20180407
 */
const webpack = require('webpack');
const pkg = require('./package.json');
const fs = require('fs');

const pubs = {
  undefined: { mode: 'none', filename: pkg.name + '.js' },
  development: { mode: 'development', filename: pkg.name + '.dev.js' },
  production: { mode: 'production', filename: pkg.name + '.prod.js' }
};
const NODE_ENV = process.env.NODE_ENV;
const pub = pubs[NODE_ENV] || pubs['undefined'];
// const SrcMainJS = __dirname + '/src/index.js';
const SrcMainJS = __dirname + '/src/com/foo.js';
const DistPath = __dirname + '/lib';

const UTBasic = require('./src/com/misc');

const now = UTBasic.genDataTimeHRStr();
const LABEL = `Auto Gen ${pkg.version} at ${now} by ${pkg.author}.`;

console.log('current publish is: ', pub);

module.exports = {
  mode: pub.mode,
  devtool: 'null',
  entry:  SrcMainJS, // 已多次提及的唯一入口文件
  output: {
    path: DistPath, // 打包后的文件存放的地方
    filename: pub.filename // 打包后输出文件的文件名
  },
  plugins:[
    new webpack.BannerPlugin(LABEL)
  ],
  // target: 'node',
  externals: {
    // 'wepy': 'wepy'
  }
};
