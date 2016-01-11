#! /bin/sh
#  string operation

str_demo()
{
    str="hello world and hello shell"
    if [ -n "$1" ]; then
        str=$1
    fi
    echo "input string is: $str"
    #1. strlen
    #1.1 
    len11=${#str}
    len12=`expr "$str" : '.*' `
    len13=`expr length "$str" `
    echo "input string length is: $len11 == $len12 == $len13"
}

str_demo
str_demo "haha"

read -p "input a string，please! " foo
str_demo "$foo"

exit 0
