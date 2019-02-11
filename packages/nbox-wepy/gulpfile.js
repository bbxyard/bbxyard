const gulp = require('gulp');
const GulpHandler = require('../../scripts/gulp-handler');
const pkg = require('./package.json');
const handler = new GulpHandler(pkg);


gulp.task('build-concat', function() {
  return handler.concat(['./src/wepy/*.js', './src/index.js']);
});

gulp.task('build-dispatch', ['build-concat'], function() {
  return handler.dispatch(pkg.main, '../nbox/lib/');
});

gulp.task('build', ['build-dispatch']);

gulp.task('default', ['build']);

gulp.task('clean', handler.clean);
