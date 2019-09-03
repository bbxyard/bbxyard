#include <stdio.h>
#include <malloc.h>

void foo(void)
{
	int* a = malloc(10 * sizeof(int));
	a[10] = 0;	// invalid write...; not free...
}

int main(int argc, char* argv[])
{
	foo();
	return 0;
}

