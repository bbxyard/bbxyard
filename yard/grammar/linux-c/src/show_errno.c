#include <stdio.h>
#include <errno.h>
#include <string.h>	// for strerror

int main(int argc, char* argv[])
{
	int i = 0;
    char prefix[64] = {0};
	for ( ; i < 255; ++i)
	{
        const char* errdesc = strerror(i);
        if (errdesc != NULL && strstr(errdesc, "Unknown error") == NULL)
        {
            fprintf(stdout, "%02d: %s\n", i, strerror(i));
            // 手工触发错误
            errno = i;
            sprintf(prefix, "by perror [%04d]", i);
            perror(prefix);
        }
	}

	return 0;
}
