#! /bin/bash
# project template

# generate grunt project
function grunt-gen()
{
    dname=$1
    NPM=cnpm
    [ x"$1" == x ] && echo "usage: $0 <dirname>" && return 1;
    mkdir $dname;
    pushd $dname
      $NPM init
      $NPM install grunt --save-dev
      $NPM install grunt-contrib-jshint   --save-dev
      $NPM install grunt-contrib-nodeunit --save-dev
      $NPM install grunt-contrib-uglify   --save-dev
      $NPM install grunt-contrib-qunit    --save-dev
      $NPM install grunt-contrib-concat   --save-dev
      $NPM install grunt-contrib-watch    --save-dev
    popd
}
