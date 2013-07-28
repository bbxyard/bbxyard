#include <stdio.h>
#include <errno.h>
#include <string.h>	// for strerror

int main(int argc, char* argv[])
{
	int i = 0;
	for ( ; i < 255; ++i)
	{
		fprintf(stdout, "%02d: %s\n", i, strerror(i));
	}

	return 0;
}

