module.exports = function(grunt) {

  // project configuration
  grunt.initConfig({
    pkg: grunt.file.readJSON("package.json"),

    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> by boxu */\n'
      },
      build: {
        src: 'src/<%= pkg.name %>.js',
        dest: 'dist/<%= pkg.name %>.min.js'
      }
    },

    qunit: {
      files: ['test/**/*.html']
    },

    jshint: {
      files: ['gruntfile.js', 'src/**/*.js', 'test/**/*.js'],
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
};
