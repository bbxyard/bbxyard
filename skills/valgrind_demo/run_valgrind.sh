#!/bin/bash
logfile=valgrind.run.log
#. analyze by valgrind
valgrind --tool=memcheck --leak-check=full --log-file=$logfile ./hello

#. show the log
echo show the runing log after 3 seconds.
sleep 3
cat $logfile


