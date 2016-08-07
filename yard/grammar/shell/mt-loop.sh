#!/bin/bash
# mult loop run
#

function run_one()
{
    cat /etc/passwd >/dev/null
    sleep 3
}

function run()
{
    for x in $*; do
        echo $x;
        run_one &
    done
    wait
}

run $*
# run_one
##./long_run.sh &
##./long_run.sh &
##./long_run.sh &
##./long_run.sh &
##./long_run.sh &

echo "wait finished"
wait
