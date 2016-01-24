#! /bin/sh
# am-i-root.sh

amiroot_1()
{
    ROOT_UID=0
    echo "input $1"
    if [ "$UID" = "$ROOT_UID" ]; then
        echo "You are root"
    else
        echo "You are just an ordinary user(but mom loves you too)."
    fi
}


amiroot_2()
{
    ROOT_NAME="root"
    MY_NAME=`whoami`
    if [ "$ROOT_NAME" = "$MY_NAME" ]; then
        echo "YOU ARE ROOT"
    else
        echo "YOU ARE NORMAL"
    fi
}

amiroot_1 "foo"
amiroot_2 "bar"

exit 0

