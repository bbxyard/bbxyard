#!/bin/bash

DNAME=$(basename $1)
[ "$DNAME" == ".git" ] && DIR=$(dirname $1) || DIR=$1
[ ! -d "$DIR/.git" ] && echo "not an git dir pass!!" && exit 1

LOG_FILE=~/var/log/git-sync.log
echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync begin!!" | tee -ai $LOG_FILE
git -C "$DIR" pull origin master 2>&1 | tee -ai $LOG_FILE
RETVAL=$?
echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync end!! status=$RETVAL" | tee -ai $LOG_FILE

exit $RETVAL

