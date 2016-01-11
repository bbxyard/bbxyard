#define _GNU_SOURCE
#include <dlfcn.h>
#include <stdio.h>
#include <errno.h>


int main(int argc, char* argv[])
{
    if (argc < 2)
    {
        fprintf(stderr, "usage: dlloader <dll>\n" );
        return 2;
    }

    void* h = dlopen(argv[1], RTLD_LAZY);
    if (NULL == h)
    {
        fprintf(stderr, "load so <%s> failed!! errcode=%d errdesc=%s\n", argv[1],  errno, dlerror() );
    }

    typedef void (*Proc_sayhello)(const char*);
    Proc_sayhello sayhello = (Proc_sayhello)dlsym(h, "sayhello");
    if (sayhello != NULL)
    {
        sayhello("boxu where are you!!");
    }

    fprintf(stdout, "print the so info\n");
    Dl_info dl_info;
    if ( 0 != dladdr(sayhello, &dl_info) )
    {
        fprintf(stdout, "path: %s\n", dl_info.dli_fname);
        fprintf(stdout, "fbase: %p\n", dl_info.dli_fname);
        fprintf(stdout, "sname: %s\n", dl_info.dli_sname);
        fprintf(stdout, "saddr: %p\n", dl_info.dli_saddr);
    }

    dlclose(h);

    return 0;
}
