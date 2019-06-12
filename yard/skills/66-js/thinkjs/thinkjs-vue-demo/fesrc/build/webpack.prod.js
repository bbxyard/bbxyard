const path = require('path')
const common = require('./webpack.common.js');
const merge = require('webpack-merge');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

module.exports = merge(common,
  {
    devtool: 'source-map',
    plugins: [
      new UglifyJSPlugin({sourceMap: true}),
      new CleanWebpackPlugin(
        ['server/www/static/*', 'server/view/*'],
        {
          // 项目根目录
          root: path.resolve(__dirname, './../..'),
        }
      ),
    ]
  }
);
