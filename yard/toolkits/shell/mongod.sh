#!/bin/bash
# mongodb ctlscript

function start()
{
	mongod --port 55555 --dbpath ~/data/mongodb --logpath ~/var/log/mongo-$(date +"%Y-%m-%d-%H-%M-%S").log --unixSocketPrefix ~/var/run  &
}

function stop()
{
	mongod --shutdown --dbpath ~/data/mongodb
}

RETVAL=0
case "$1" in
        start)
                start
                ;;
        stop)
                stop
                ;;
        restart)
                stop
                start
                ;;
        *)
                echo $"Usage: $0 {start|stop|restart}"
                RETVAL=1
esac
exit $RETVAL
