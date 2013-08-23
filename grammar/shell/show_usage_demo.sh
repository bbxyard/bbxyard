#!/bin/bash
# demo show usage

AUTHOR=bbxyard@gmail.com
COPY_RIGHT=

show_usage()
{
    cat << EOF
Usage: show_usage_demo [OPTION]

Known values for OPTIONS are:
  --prefix[=DIR]    change prefix to DIR
  --bindir          print location where binaries are installed
  --includes        print include information
  --includedir      print location where headers are installed
  --ldflags
  --libs            print library information
  ...
  --help            print this help

Copyright (C) 2013 Free Software Foundation, Inc.
write by $AUTHOR.
EOF
}

if test $# -eq 0; then
    show_usage
    exit 2
fi

while test $# -gt 0; do

  case "$1" in
  --prefix)
  echo show prefix
  exit 0
  ;;
  --includes)
  echo show includes
  exit 0
  ;;
  --help)
  show_usage
  exit 0
  ;;
  *)
  show_usage
  exit 1
  ;;
  esac
done

exit 0

