#include "test.h"
char* test_hello(const char* name) {
    const char* hello=" -> hello";
    char* result=(char*)yh_c_buffer_malloc(sizeof(char)*(strlen(name)+strlen(hello)) + 1);
    strcpy(result,name);
    strcat(result,hello);
    return result;
}

void* yh_c_buffer_malloc(unsigned int size) {
    void* p = malloc(size);
    return p;
}

void yh_c_buffer_free(void* p) {
    free(p);
}
