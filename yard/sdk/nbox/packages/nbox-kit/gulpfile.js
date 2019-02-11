const gulp = require('gulp');
const GulpHandler = require('../../scripts/gulp-handler');
const pkg = require('./package.json');
const handler = new GulpHandler(pkg);

const inArr = [
  './src/com/*.js',
  './src/fs/*.js',
  './src/rpc/*.js',
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
