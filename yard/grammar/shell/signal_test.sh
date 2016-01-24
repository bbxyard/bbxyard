#! /bin/sh

Int14Proc()
{
    echo hello and make a interaput!! 
    exit 14
}

trap Int14Proc 14

echo wait a signal...
echo "my pid is: $$"
sleep 30

echo 0
