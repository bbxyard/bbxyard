#!/bin/bash
# case test

function do_query()
{
    type=$1
    s=$2
    RETVAL=-2
    for ((i=0; i<1; ++i))
    {
        case "$type" in
            day)
                echo $s | grep -io "Monday" && return 1
                echo $s | grep -io "Tuesday" && return 2
                echo $s | grep -io "Wensday" && return 3
                echo $s | grep -io "Thursday" && return 4
                echo $s | grep -io "Friday" && break
                echo $s | grep -io "Saturday" && return 6
                echo $s | grep -io "Sunday" && return 7
                echo "Unknown" && return -1;
                ;;
            month)
                echo $s not supp
                ;;
            year) echo "$s NOT impl! please wait!!";;
            *)
                echo "sorry I have no idea! 1=$1 2=$2"
                return 5
                ;;
        esac
    }
    return $RETVAL
}

type=${1:-day}
request=${2:-"hello Monday so nice!"}
echo "type=$type, request=$request"
QQ=$(do_query $type "$request")
echo "Query=$QQ, exit=$?"
