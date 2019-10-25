#! /bin/sh

function handle_signal_int() {
    echo "Recv CTRL - C"
    exit 2
}

function handle_signal_kill() {
    echo "Recv Kill -9"
    exit 9
}

function handle_signal_term() {
    echo "Rec SIGTERM"
    exit 15
}

function handle_signal_14() {
    echo hello and make a interaput!! 
    exit 14
}

function handle_signal_x() {
    echo "RECV SIGNAL and EXIT"
    exit -1
}

# trap handle_signal_kill KILL
# trap handle_signal_term TERM
# trap handle_signal_int INT
trap handle_signal_14 14
# trap handle_signal_x 14 KILL TERM INT

echo wait a signal...
echo "my pid is: $$"
sleep 30

echo 0
