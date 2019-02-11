const gulp = require('gulp');
const GulpHandler = require('../../scripts/gulp-handler');
const pkg = require('./package.json');
const handler = new GulpHandler(pkg);

/**
 * @name inArr
 * @description
 *    com目录 存在顺序信赖，所以要单独列出来
 *    foobar目录 可以使用通配符
 */
let inArr = [
  './src/com/misc.js',
  './src/com/strfmt.js',
  './src/com/logger.js',
  './src/com/validator.js',
  './src/foobar/*.js',
  './src/index.js'
];

gulp.task('build-concat', function() {
  return handler.concat(inArr);
});

gulp.task('build-dispatch', ['build-concat'], function() {
  return handler.dispatch(pkg.main, '../nbox/lib/');
});

gulp.task('build', ['build-dispatch']);

gulp.task('default', ['build']);

gulp.task('clean', handler.clean);
