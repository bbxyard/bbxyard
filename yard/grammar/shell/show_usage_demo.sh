#!/bin/bash
# demo show usage

AUTHOR=bbxyard@gmail.com
COPY_RIGHT=

function show_usage()
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
  --outfile -o      output file  
  ...
  --help            print this help

Copyright (C) 2013 Free Software Foundation, Inc.
write by $AUTHOR.
EOF
}

function write_to_file()
{
    cat >> $1 << EOF
Usage: show_usage_demo [OPTION]

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

Copyright (C) 2013 Free Software Foundation, Inc.
write by $AUTHOR.
EOF
}

function main()
{
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
      --outfile|-o)
        echo show outfile
        write_to_file "$2"
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
}


main $@
