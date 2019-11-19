#!/bin/bash
# 错误捕捉

function error_handler() {
  errcode=$? # save the exit code as the first thing done in the trap function
  echo "last error code is: $errorcode"
  echo "[${BASH_LINENO[0]}]: $BASH_COMMAND"
  exit $errcode
}

function debug_handler() {
  echo "[${LINENO}]Hallo, Das ist debug[$?]... [${BASH_LINENO[0]}] ${FUNCNAME[1]}"
}

function return_handler() {
  echo "hooked return value: $?"
  errcode=$? # save the exit code as the first thing done in the trap function
  echo "last error code is: $errorcode"
  echo "[${BASH_LINENO[0]}]: $BASH_COMMAND"
  exit $errcode
}

function test_normal() {
  echo "hey this is a normal function"
  sleep 1
  return 0
}

function test_normal2() {
  echo "hey THIS IS A NORMAL FUNCTION"
  sleep 1
  return 2
}

function test_raise_an_error() {
  echo "==> ${LINENO}/${FUNCNAME[0]}"
  (touch no-perm.txt && ./no-perm.txt) || true
}

function test_raise_an_error_wrapper() {
  echo "==> ${LINENO}/${FUNCNAME[0]} RAISE AN ERROR"
  test_raise_an_error
  ret=$?
  echo "==> ${LINENO}/${FUNCNAME[0]} CAN NOT SEE THIS"
  rm no-perm.txt
  # exit $ret
  return $ret
}

# Registry
set -e
trap error_handler ERR
trap debug_handler DEBUG
trap return_handler RETURN

# 开始实验
function run_test() {
  test_normal
  test_raise_an_error
  test_normal2
  # test_raise_an_error_wrapper
}

function run_test2() {
  echo "[run_test2] hey hey. cannot see this line."
}

function run_test_1_2() {
  run_test
  run_test2
  echo "HA HA. It is not good..."
}

run_test
run_test2
# run_test_1_2
