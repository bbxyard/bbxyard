package main

/*
#include "c/test.c"
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

	cs2 := C.test_hello(C.CString("C-IAmC-2"))
	fmt.Println("Another:", C.GoString(cs2))
	C.yh_c_buffer_free(unsafe.Pointer(cs2))

	fmt.Println("done")
}
