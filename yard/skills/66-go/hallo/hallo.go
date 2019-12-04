package main

import (
	"fmt"
	"runtime"
)

func main2() {
	fmt.Println(runtime.GOARCH)
	fmt.Println(runtime.GOOS)
}
