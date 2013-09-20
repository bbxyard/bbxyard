#include <stdio.h>

int main()
{
	const char* psz = "MemTotal:       507480 kB";
	int val = 0;
	psz = strchr(psz, ':') + 1;
	sscanf(psz, "%d", &val);
	printf("val=%d\n");
	
	char name[256];
  psz = "Name:   binary_name";
  sscanf(psz, "%*s %s", name);
  printf("name=%s\n", name);
	return 0;
}