#!/bin/bash
# @file: text-conv-demo.sh
# @date: 2018-08-08 15:26:44
# @tver: 2018-08-08
# 
# text to picture conv demo
#
# @install: 
#   [Redhat] sudo yum install -y wget 
#   [Mac] brew install wget 
#
# @depends: wget easy_install pip wordcloud
# @memo:
#  中文必须指定字体全路径
#

# User Var defined
AUTHOR=boxu@yvhai.com
VERSION="0.1.0"
YEAR_BEGIN=2013

# User Var derived
THIS_NAME=$(basename $0)
PROJ_ROOT_HOME=$(pushd `dirname $0` >/dev/null; pwd; popd >/dev/null);
YEAR=$(date +%Y)
COPY_RIGHT="${THIS_NAME} ${VERSION} Copyright (C) ${YEAR_BEGIN}-${YEAR}, ${AUTHOR}"

#depends
WC="tag2wc.py"

# aux utils
# color echo
NORMAL=$(tput sgr0)
RED=$(tput setaf 1; tput bold)
BLUE=$(tput setaf 4; tput bold)
GREEN=$(tput setaf 2; tput bold)
YELLOW=$(tput setaf 3; tput bold)
function red()    { echo -e "$RED$*$NORMAL"; }
function blue()   { echo -e "$BLUE$*$NORMAL"; }
function green()  { echo -e "$GREEN$*$NORMAL"; }
function yellow() { echo -e "$YELLOW$*$NORMAL"; }

function init() {
  pushd "$PROJ_ROOT_HOME" >/dev/null
  red "Current dir is: $(pwd 2>/dev/null)"
  popd >/dev/null
  pushd "$PROJ_ROOT_HOME"
  IN_DIR="./in"
  OUT_DIR="./out"
  [ ! -d "$OUT_DIR" ] && mkdir -p "$OUT_DIR"
  cp -rvf "$IN_DIR"/* "$OUT_DIR/"
  # 公共变量
  outext="jpg"
}

function bar() {
  blue "$@"
}

function foobar() {
  blue "$@"
}

function wc() {
  opt="-t /etc/passwd"
  outbase="$OUT_DIR/txt2pic"
  outext="png"
  # basic
  $WC $opt -p "${outbase}-default.${outext}"
  $WC $opt -p "${outbase}-400x-orange.${outext}" -c orange
  $WC $opt -p "${outbase}-400x200-red.${outext}" -W400 -H200 -c red
  $WC $opt -p "${outbase}-x200-bg#gray.${outext}" -H200 -b '#e8e8e8'
  $WC $opt -p "${outbase}-x200-red-bg#gray.${outext}" -H200 -b 'gray' -c red
  # with font
  $WC $opt -p "${outbase}-font-msyhbd.${outext}" -f /tmp/msyhbd.ttf
  # with mask, 宽、高随图片变化
  $WC $opt -p "${outbase}-mask.${outext}" -m in/mask-map.jpg
  $WC $opt -p "${outbase}-mask-M.${outext}" -m in/mask-map.jpg -M
  $WC $opt -p "${outbase}-mask-M-bg#black.${outext}" -m in/mask-map.jpg -b 'black'
  $WC $opt -p "${outbase}-mask-c-bg#black.${outext}" -m in/mask-map.jpg -b 'black' -c orange
}

function wc-cn() {
  opt="-t ./foo.txt"
  outbase="$OUT_DIR/txt2pic-cn"
  outext="png"
  # with mask, 宽、高随图片变化
  $WC $opt -p "${outbase}-mask-M.${outext}" -m in/mask-map.jpg -M

  opt="-t ./tags.txt"
  outbase="$OUT_DIR/txt2pic-tags"
  $WC $opt -p "${outbase}-mask-M.${outext}" -m in/mask-map.jpg -M
}

function showUsage() {
cat << EOF
${COPY_RIGHT}
Usage: ${THIS_NAME} <cmd> [OPTION]

Options:

  -v, --version                             output the version number
  -h, --help                                output usage information

Commands:

  wc [options] <arg1> <arg2>                demo-wc
  wc-cn [options] <arg1> <arg2>             demo-wc-cn
  bar [options] <arg1> <arg2>               demo-bar
  foobar|fb [options] <inDir> <outFile>     demo-foobar

Known values for OPTIONS are:
  --prefix[=DIR]    change prefix to DIR
  --bindir          print location where binaries are installed
  --includes        print include information
  --includedir      print location where headers are installed
  --ldflags
  --libs            print library information
  --outfile -o      output file  
  ...
  --help            print this help


EOF
}

RETVAL=0
function main() {
  if [ $# -eq 0 ]; then
    showUsage
    return 1
  fi

  init
  sub="$1"
  shift
  case "$sub" in
    wc) wc "$@" ;;
    wc-cn) wc-cn "$@" ;;
    bar) bar "$@" ;;
    foobar|fb) foobar "$@" ;;
    *) showUsage ;;
  esac

  return $RETVAL
}

main "$@"
