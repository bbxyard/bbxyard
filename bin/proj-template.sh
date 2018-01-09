#! /bin/bash
# project template

# generate grunt project
function xb-grunt-gen()
{
    dname=$1
    NPM=cnpm
    [ x"$1" == x ] && echo "usage: $0 <dirname>" && return 1;
    mkdir "$dname";
    pushd "$dname"
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

# generate vue project
function xb-vue-gen()
{
    # for advanced demo
    # use vue init webpack vue-adv-demo
    dname=$1
    NPM=cnpm
    [ x"$1" == x ] && echo "usage: $0 <project_name>" && return 1;
    cnpm install -g vue-cli
    vue init webpack-simple "$dname"
    pushd "$dname"
      cnpm install
      cnpm run dev # ready to go!
    popd
}


# get git-ignore-template
function xb-git-ignore() {
  #statements
  IGHOME="https://raw.githubusercontent.com/bbxfork/gitignore/master"
  OUT_OPT="-o.gitignore"
  type=$1
  case "$type" in
    wepy) curl "$IGHOME/me/wepy.gitignore" $OUT_OPT;;
    c++|cpp) curl "$IGHOME/C++.gitignore" $OUT_OPT;;
    c) curl "$IGHOME/C.gitignore" $OUT_OPT;;
    python) curl "$IGHOME/Python.gitignore" $OUT_OPT;;
    *)
      echo "default($type) NOT SUPPORT!" && echo "Usage: xb-git-ignore <type>";
      ;;
  esac
}
