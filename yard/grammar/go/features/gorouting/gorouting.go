package main

import (
	"fmt"
	"time"
)

func highCoRun() {
	for i := 0; i < 1000; i++ {
		go func(id int) {
			for {
				fmt.Printf("Ich komme aus goroutine %d\n", id)
			}
		}(i)
	}
}

func main() {
	highCoRun()
	time.Sleep(time.Second * 3)
}
