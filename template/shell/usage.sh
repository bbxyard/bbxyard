#!/bin/bash
# XX tools
#
# install: sudo yum install -y xx
# depends: a, b, d
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


function init() {
 echo 'to do some Init'
}

function func1() {
  echo 'func1'
}


function showUsage() {
cat << EOF
${COPY_RIGHT}
Usage: ${THIS_NAME} [OPTION]

Options:

  -v, --version                             output the version number
  -h, --help                                output usage information

Commands:

  bar [options] <arg1> <arg2>               demo-bar
  dir-index|di [options] <inDir> <outFile>  目录索引化

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
    func1) resize "$@" ;;
    *) showUsage ;;
  esac
  
  return $RETVAL
}

main "$@"
