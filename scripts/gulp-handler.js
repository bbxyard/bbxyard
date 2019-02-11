var gulp = require('gulp');
var gconcat = require('gulp-concat');
var gclean = require('gulp-clean');
var grename = require('gulp-rename');

var chalk = require('chalk');
var fs = require('fs');


class GulpTaskHolder {
  constructor(pkg) {
    this.pkg = pkg;
    this.mainJS = this.pkg.main;
    this.distJS = this.pkg.name + '.js';
    this.distDir = '../nbox/lib/';
  }

  static doConcat(inArr, outFile) {
    // const outDir = final ? './lib' : './';
    const outDir = './';
    let stream = gulp.src(inArr)
      .pipe(gconcat(outFile, {newLine: '\n'}))
      .pipe(gulp.dest(outDir));
    return stream;
  }

  clean() {
    gulp.src(['./build/**/*', './lib/**/*'], {read: false}).pipe(gclean());
  }

  concat(inArr) {
    return GulpTaskHolder.doConcat(inArr, this.mainJS, true);
  }

  dispatch(ins, out) {
    // return gulp.src('./lib/*.js').pipe(gulp.dest(this.distDir));
    return gulp.src(ins).pipe(grename(this.distJS)).pipe(gulp.dest(out));
  }  
};

module.exports = GulpTaskHolder;
