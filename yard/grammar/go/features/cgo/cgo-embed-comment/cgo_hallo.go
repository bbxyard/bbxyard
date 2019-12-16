package main

/*
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

char* test_hello(const char* name){
    const char* hello=" -> hello";
    char* result=(char*)malloc(sizeof(char)*(strlen(name)+strlen(hello)) + 1);
    strcpy(result,name);
    strcat(result,hello);
    return result;
}
*/
import "C"
import (
	"fmt"
	"unsafe"
)

func main() {
	cs := C.test_hello(C.CString("C-IAmC"))
	fmt.Println(C.GoString(cs))
	C.free(unsafe.Pointer(cs))
	// fmt.Println("call C.sleep for 3s")
	// C.sleep(3)
	fmt.Println("done")
}
