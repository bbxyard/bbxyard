#!/usr/bin/env bash

# performance optimize
echo 1 >/sys/kernel/mm/ksm/run
echo 1000 >/sys/kernel/mm/ksm/sleep_millisecs

# start script
/opt/netdata/usr/sbin/netdata

# dead-loop
tail -f /dev/null
