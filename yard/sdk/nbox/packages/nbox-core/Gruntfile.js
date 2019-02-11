module.exports = function(grunt) {
  var now = grunt.template.today("yyyy-mm-dd");
  var year = grunt.template.today("yyyy");
  var pkg = grunt.file.readJSON("package.json");
  var banner = `\n/* Auto Gen ${pkg.name}-${pkg.version} on ${now} by ${pkg.author} */\n\n`;
  var footer = `\n/* Copyright (C) 2013-${year} ${pkg.author}. All rights reserved */\n`;
  var bannerUglify = `/*! Auto Gen and Uglify ${pkg.name}-${pkg.version} on ${now} by ${pkg.author} */\n`;

  // project configuration
  grunt.initConfig({
    pkg: pkg,

    concat: {
      options: { 
        separator: '\n',
        banner: banner,
        footer: footer
      },
      sub: {
        files: {
          'build/com.js': ['src/com/misc.js', 'src/com/validator.js', 'src/com/strfmt.js', 'src/com/logger.js', 'src/com.js'],
          'build/wx.js': ['src/wx/misc.js', 'src/wx.js'],
          'build/foobar.js': ['src/foobar/foo.js', 'src/foobar/bar.js', 'src/foobar.js']
        }
      },
      all: {
        src: ['build/com.js', 'build/wx.js', 'build/foobar.js', 'src/index.js'],
        dest: "lib/<%= pkg.name %>.js"
      }
    },

    uglify: {
      options: {
        banner: bannerUglify
      },
      dist: {
        src: 'lib/<%= pkg.name %>.js',
        dest: 'lib/<%= pkg.name %>.min.js'
      }
    },

    qunit: {
      files: ['test/**/*.js']
    },

    log: {
      foo: [1985, 7, 18],
      bar: 'XuBo',
      xyz: false
    }
  });

  // load task plugs
  grunt.loadNpmTasks("grunt-contrib-concat");
  grunt.loadNpmTasks("grunt-contrib-uglify");

  // the default task to run
  grunt.registerTask('default', ['concat']);

  // adv
  grunt.registerTask('dev', ['concat', 'uglify']);
  grunt.registerTask('test', ['qunit']);
  grunt.registerTask('all', ['concat', 'uglify', 'qunit']);

  // multiple task
  var PKG = grunt.file.readJSON("package.json");
  var PKG_BUF = grunt.file.read("package.json");
  var LOG_PREFIX = grunt.template.today("yyyy-mm-dd") + " ";
  grunt.registerMultiTask('log', 'Log STUFF', function(){
    grunt.log.writeln(LOG_PREFIX + this.target + ": " + this.data);
  });

  grunt.registerTask('haha', 'A sample task that logs stuff.', function(arg1, arg2){
    if (arguments.length === 0)
      grunt.log.writeln(LOG_PREFIX + this.name + ": NO ARGS");
    else
      grunt.log.writeln(LOG_PREFIX + this.name + ": " + arg1 + " " + arg2);
  });

  grunt.registerTask('build', 'build', function() {
    grunt.log.writeln("build: concat");
    grunt.task.run(['concat']);
    // grunt.log.writeln('clean build dir');
    // grunt.file.delete("build");
  });

  grunt.registerTask('clean', 'clean build and swap files', function() {
    grunt.file.delete('build');
    grunt.file.delete('dist');
  });

  grunt.registerTask('fs', 'FS Demo', function(){
    grunt.log.writeln("fs grunt.file.* demo");
    grunt.file.mkdir("dist/fs/foo/bar");
    grunt.file.copy("package.json", "dist/fs/foo/bar/pkg.json");
    grunt.file.write("dist/fs/foo/pkg.json", JSON.stringify(PKG));
    grunt.file.write("dist/fs/foo/pkg2.json", PKG_BUF);
    grunt.file.recurse("dist", function(abspath, rootdir, subdir, filename){
      grunt.log.writeln("  -- " + abspath + "|" + subdir + "|" + filename);
    });
    grunt.file.delete("dist/fs");
  });
};
