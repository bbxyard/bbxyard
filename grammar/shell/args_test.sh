#! /bin/sh

if [ -z "$1" ]; then
    echo "you must input at least one argument!!"
    echo "usage: $(basename $0) arg1 arg2 arg3 ... "
    exit 1
fi

#. test $*
test_xin()
{
    echo "list arguments with \"\$*\""
    ix=0
    for arg in "$*"
    do
        echo "arg #$ix = $arg"
        let "ix += 1"
    done
}

#. test $@
test_at()
{
    echo 'echo $@'
    echo "list arguments with \"\$@\""
    ix=0
    for arg in "$@"
    do
        echo "arg$ix: $arg"
        ix=$ix+1
    done
}

test_xin "$*"
test_at "$@"

