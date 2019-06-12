
const { resolve, join } = require('path')
const glob = require('glob')
module.exports = {
  plugins: {
    'postcss-import': {
      path: [resolve('../fesrc')],
    },
    'postcss-for': {},
    'postcss-each': {},
    'postcss-mixins': {},
    'postcss-cssnext': {},
    'postcss-nested': {},
    'postcss-url': {},
  },
}
