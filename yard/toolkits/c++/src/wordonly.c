#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <ctype.h>

int wordonly(const char* srcfile, const char* dstfile)
{
	FILE* fsrc = fopen(srcfile, "rb");
	FILE* fdst = fopen(dstfile, "wb");
	if (NULL == fsrc || NULL == fdst)
	{
		fprintf(stdout, "open i/o file failed!!\n");
		return errno;
	}
	
	char buf[1024] = {0};	
	int  rd = 0;
	while ( (rd = fread(buf, 1, sizeof(buf), fsrc)) != 0)
	{
		const char* end = buf + rd;
		char* psz = buf;
		while (psz != end)
		{
			if ( *psz > 32 && !isalpha(*psz) )
			{
				*psz = ' ';
			}	
			++psz;
		}
		fwrite(buf, 1, rd, fdst);
	}

	fclose(fsrc);
	fclose(fdst);
}

int main(int argc, char* argv[])
{
	if (argc != 3)
	{
		fprintf(stdout, "usage: wordonly <srcfile> <dstfile>\n");
		return 3;
	}

	int ret = wordonly(argv[1], argv[2]);
	fprintf(stdout, "wordonly done!!\n");
	return ret;
}

