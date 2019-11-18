#!/bin/bash
# 错误捕捉

function error_handler() {
  errcode=$? # save the exit code as the first thing done in the trap function
  echo "error $errorcode"
  echo "the command executing at the time of the error was"
  echo "$BASH_COMMAND"
  echo "on line ${BASH_LINENO[0]}"
  # do some error handling, cleanup, logging, notification
  # $BASH_COMMAND contains the command that was being executed at the time of the trap
  # ${BASH_LINENO[0]} contains the line number in the script of that command
  # exit the script or return to try again, etc.
  # creating stack...
  exit $errcode  # or use some other value or do return instead
}

function debug_handler() {
  echo "Hallo, Das ist debug... $@"
}

function return_handler() {
  echo "hooked return value: $?"
}

trap error_handler ERR
trap debug_handler DEBUG
trap return_handler RETURN

function test_normal() {
  echo "hey this is a normal functio"
  sleep 1
}

function test_perm() {
  touch no-perm.txt && ./no-perm.txt
  ret=$?
  rm no-perm.txt
  # exit $ret
  return $ret
}

# 开始实验
test_normal
test_perm

echo "hey hey. cannot see this line."

