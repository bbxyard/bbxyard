#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <syslog.h>


static int run(int argc, char* argv[])
{
	while (1)
	{
		syslog(LOG_CRIT, "sub <%s> running!", argv[0]);
		sleep(1);
	}
	return 0;
}

static int daemonize(int argc, char* argv[])
{
	int ret = 0;
	pid_t  pid;

	/*
	 * Become a session leader to lose controlling TTY.
	 */
	if ((pid = fork()) < 0)
	{
		perror("fork");
		exit(1);
	}
	else if (pid != 0) /* parent */
	{
		fprintf(stdout, "parent exited!!\n");
		exit(ret);
	}

	/* sub process */
	setsid();

	/*
	 * Change the current working directory to the root.
	 */
	if (chdir("/") < 0) {
		perror("chdir");
		exit(1);
	}

	/*
	 * Attach file descriptors 0, 1, and 2 to /dev/null.
	 */
	close(0);
	open("/dev/null", O_RDWR);
	dup2(0, 1);
	dup2(0, 2);

	/* sub process run */
	ret = run(argc, argv);
	return ret;
}

int main(int argc, char* argv[])
{
	int ret = daemonize(argc, argv);
	return ret;
}
