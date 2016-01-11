#!/bin/bash

if [ -z "$1" ]; then
    echo "usage: $0 <git_dir>"
    exit 1
fi


DNAME=$(basename $1)
[ "$DNAME" == ".git" ] && DIR=$(dirname $1) || DIR=$1
[ ! -d "$DIR/.git" ] && echo "$1 not an git dir pass!!" && exit 2

LOG_FILE=~/var/log/git-sync.log
echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync begin!!" | tee -ai $LOG_FILE
git -C "$DIR" pull origin master 2>&1 | tee -ai $LOG_FILE
RETVAL=$?
echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync end!! status=$RETVAL" | tee -ai $LOG_FILE

exit $RETVAL

