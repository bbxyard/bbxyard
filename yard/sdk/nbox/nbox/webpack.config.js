const webpack = require('webpack');

const pubs = {
  undefined: { mode: 'none', filename: 'main.js' },
  development: { mode: 'development', filename: 'main.dev.js' },
  production: { mode: 'production', filename: 'main.prod.js' }
};
const NODE_ENV = process.env.NODE_ENV;
const pub = pubs[NODE_ENV] || pubs['undefined'];
const LABEL = '';

console.log('current publish is: ', pub);

module.exports = {
  mode: pub.mode,
  devtool: 'null',
  entry:  __dirname + "/lib/utils/util.js", //已多次提及的唯一入口文件
  output: {
    path: __dirname + "/dist", //打包后的文件存放的地方
    filename: pub.filename //打包后输出文件的文件名
  },
  plugins:[
    new webpack.BannerPlugin('Just do it.')
  ],
  externals: {
    'wepy': 'wepy',
    'sprintf-js': 'sprintf-js'
  }
};
