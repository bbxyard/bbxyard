#include <stdlib.h>
#include <dlfcn.h>
#include <stdio.h>
#include <string.h>

int loadso(const char* file)
{
	void* h = dlopen(file, RTLD_LAZY);
	if (NULL != h)
	{
		fprintf(stdout, "load file %s Ok, handle=%p\n", file, h);
		dlclose(h);
	}
	else
	{
		fprintf(stdout, "load file %s failed, err=%s\n", file, dlerror() );
	}
	return 0;
}

int main(int argc, char* argv[])
{
	const char* so_name = "libfoo.so";
	const char* so_dir = "/home/extend/test/libs";

	char ld_path[1024] = {0};
	const char* psz_ld_path = getenv("LD_LIBRARY_PATH"); 
	const char* psz_path = getenv("PATH");
	if (psz_ld_path != NULL)
		strcpy(ld_path, psz_ld_path);
	sprintf(ld_path + strlen(ld_path), ":%s", so_dir);
	fprintf(stdout, "org: ld_path = %s\n", psz_ld_path);
	loadso(so_name);

	setenv("LD_LIBRARY_PATH", ld_path, 1);
	psz_ld_path = getenv("LD_LIBRARY_PATH");
	fprintf(stdout, "new: ld_path = %s\n", psz_ld_path);
	loadso(so_name);
	return 0;
}
