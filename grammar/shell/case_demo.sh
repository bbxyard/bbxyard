#! /bin/bash

if [ $# -lt 1 ]; then
    echo at least one params!!
    exit 1
fi;

choice=$1
echo -e "your input is: $1\n"

#. * * * * * * * * * * * * *
#. * * * 
#. * * * * * * * * * * * * *
get_yn()
{
    yes_ret=0
    no_ret=1
    while echo "enter y/n: "
    do
        read yn
        case $yn in
            [Yy])   return 0;;
            yes)    return 0;;
            YES)    return 0;;
            [Nn])   return 1;;
            no)     return 1;;
            NO)     return 1;;            
            * )     
                echo "only accept Y,y,N,n,YES,yes,NO,no" ;;
        esac
    done
}

#. test case
case "$choice" in
    1) echo one;;
    2) echo two;;
    "345") echo "large than 2";;
    *) echo "not found!!";;
esac;

get_yn ABC
get_yn Y

