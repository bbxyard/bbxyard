const path = require('path')
const common = require('./webpack.common.js');
const merge = require('webpack-merge');
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = merge(common,
  {
    plugins: [
      new CleanWebpackPlugin(
        // dev环境不清理view下的内容，因为open时文件没有，thinkjs找不到模板
        ['/server/www/static/*', /* 'view/*' */],
        {
          // 项目根目录
          root: path.resolve(__dirname, './../../'),
        }
      ),
    ],
    // 开启source-map
    devtool: 'source-map',
    devServer: {
      proxy: {
        '*': 'http://localhost:8360'
      },
      port: '8400',
      publicPath: '/static/',
      contentBase: '../../www/static/',
      // run dev 打开浏览器
      // open: true,
      // 关闭无用log，只有在报错和warning时报错
      // noInfo: true,
    },
  }
);
