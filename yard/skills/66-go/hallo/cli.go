package main

import (
	"fmt"
	"runtime"
)

func main() {
	n1 := 100
	fmt.Println("n1 is: {}", n1)
	fmt.Println(runtime.GOOS)
	fmt.Println(runtime.Compiler)
}
