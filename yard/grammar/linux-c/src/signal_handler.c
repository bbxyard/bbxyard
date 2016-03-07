#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>


/**
 * 注册信号处理
 */
typedef void (*Proc_signal_handler)(int sig);
static void reg_signal_handler(int signal, Proc_signal_handler handler)
{
    /* Set signal handlers */
    sigset_t sigset;
    sigemptyset(&sigset);
    struct sigaction siginfo = {
        .sa_handler = handler,
        .sa_mask = sigset,
        .sa_flags = SA_RESTART,
    };
    sigaction(signal, &siginfo, NULL);
}

static void sighandler(int signal)
{
	fprintf(stdout, "Received signal %d: %s.  Shutting down.\n", signal, strsignal(signal));
    if (signal == SIGTERM)
    {
        exit(signal);
    }
}

int main(int argc, char* argv[])
{
    reg_signal_handler(SIGINT, sighandler);
    reg_signal_handler(SIGTERM, sighandler);
    reg_signal_handler(9, sighandler);
    fprintf(stdout, "%s is running!\n", argv[0]);
    while (1)
    {
        sleep(1);
    }
    return 0;
}
