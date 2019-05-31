module.exports = function(grunt) {

  // project configuration
  grunt.initConfig({
    pkg: grunt.file.readJSON("package.json"),

    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> by boxu */\n'
      },
      dist: {
        src: 'src/<%= pkg.name %>.js',
        dest: 'dist/<%= pkg.name %>.min.js'
      },
      diy: {
        src: 'src/jurl.js',
        dest: 'dist/jurl.min.js'
      }
    },

    qunit: {
      files: ['test/**/*.html']
    },

    jshint: {
      files: ['Gruntfile.js', 'src/**/*.js', 'test/**/*.js'],
      options: {
        //这里是覆盖JSHint默认配置的选项
        globals: {
          jQuery: true, console: true, module: true, document: true
        }
      }
    },

    concat: {
      options: { separator: ';' },
      dist: {
        src: ['src/*.js'],
        dest: "dist/<%= pkg.name %>.concat.js"
      }
    },

    watch: {
      files: ['<%= jshint.files %>'],
      tasks: ['jshint', 'qunit']
    },

    log: {
      foo: [1985, 7, 18],
      bar: 'XuBo',
      xyz: false
    }
  });

  // load task plugs
  grunt.loadNpmTasks("grunt-contrib-uglify"); // minify
  grunt.loadNpmTasks("grunt-contrib-qunit");
  grunt.loadNpmTasks("grunt-contrib-jshint");
  grunt.loadNpmTasks("grunt-contrib-concat");
  grunt.loadNpmTasks("grunt-contrib-watch");

  // the default task to run
  grunt.registerTask('default', ['uglify']);

  // adv
  grunt.registerTask('dev', ['uglify', 'concat']);
  grunt.registerTask('test', ['qunit']);
  grunt.registerTask('all', ['uglify', 'concat', 'jshint', 'watch']);

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

  grunt.registerTask('xixi', 'Simple task', function(){
    grunt.log.writeln("Currently running the task: ", this.name);
    grunt.task.requires('uglify');  // add depends
    grunt.task.run(['uglify', 'concat']);
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
