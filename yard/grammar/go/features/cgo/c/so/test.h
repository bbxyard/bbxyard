#ifndef TEST_CGO_H
#define TEST_CGO_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

char* test_hello(const char* name);
void* yh_c_buffer_malloc(unsigned int size);
void yh_c_buffer_free(void* p);

#endif /* TEST_CGO_H */
