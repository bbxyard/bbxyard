#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* test_hello(const char* name) {
    const char* hello=" -> hello";
    char* result=(char*)malloc(sizeof(char)*(strlen(name)+strlen(hello)) + 1);
    strcpy(result,name);
    strcat(result,hello);
    return result;
}


int main()
{
    char* str=test_hello("#Code of C#");
    printf("%s\n",str);
    free(str);
}

