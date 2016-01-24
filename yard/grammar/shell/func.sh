#! /bin/sh

say_hello()
{
    echo "hello: $1"
}

say_hello $*
say_hello $@


