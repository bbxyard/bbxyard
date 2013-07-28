#include <unistd.h>
#include <stdio.h>


int main(int argc, char* argv[])
{
    FILE* fp = popen("uname -a", "r");
    char tmp[1024] = {0};
    fgets(tmp, 1024, fp);
    fprintf(stdout, "hello: %s\n", tmp); 
    pclose(fp);

    return 0;
}
