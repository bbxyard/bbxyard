#include <stdio.h>

void _init()
{
    fprintf(stdout, "I am inited!!\n");
}

void _fini()
{
    fprintf(stdout, "I am finished!!\n");
}

void sayhello(const char* msg)
{
    fprintf(stdout, "hello: %s\n", msg);
}
