#!/bin/bash
# trap error


function error_handler() {
  echo "${LINENO}/${FUNCNAME[0]} Hi, I am the error handler and lasterror is: $?"
}

function func1() {
  echo "$(date +'%Y-%m-%d %H:%M:%S') ${FUNCNAME[0]} hallo $1"
  return $1
}

function func2() {
  echo "*** $(date +'%Y-%m-%d %H:%M:%S') ${FUNCNAME[0]} hallo $1"
  echo "  ==> try to create dir and raise an error"
  mkdir dir1/dir2
  RET=$?
  echo "  ==> error must be printed. 实质处理，可以中断处理"
  echo "*** ${FUNCNAME[0]} done ***"
  return $RET
}

trap error_handler ERR

func1 0
func1 2
func1 22
func2
func1 0
func1 511
# mkdir /bin/haha
