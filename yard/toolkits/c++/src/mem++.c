#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void addmem(int block_size, int block_cnt)
{
    char* buff = NULL;
    int i = 0;
    for (; i < block_cnt; ++i)
    {
        buff = (char*)malloc(block_size);
        memset(buff, 0, sizeof(buff));
    }
}

int main(int argc, char* argv[])
{
    int block_size = 0;
    int block_cnt = 0;
    while (1)
    {
        fprintf(stdout, "input block size and count: ");
        scanf("%d %d", &block_size, &block_cnt);
        addmem(block_size, block_cnt);
        fprintf(stdout, "waste mem: %d * %d = %d\n", block_size, block_cnt, block_size * block_cnt);
    }

	return 0;
}
