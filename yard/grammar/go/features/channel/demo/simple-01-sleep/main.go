package main

import (
	"fmt"
	"time"
)

const NUM = 10

func doWork(workerId int, c chan int) {
	for n := range c {
		fmt.Printf("Worker %d handled %c\n", workerId, n)
	}
}

func createWorker(workerId int) chan<- int {
	out := make(chan int)
	go doWork(workerId, out)
	return out
}

func simpleChanDemo() {
	var channels [NUM]chan<- int
	for i := 0; i < NUM; i++ {
		channels[i] = createWorker(i)
	}
	for i := 0; i < NUM; i++ {
		channels[i] <- 'a' + i
	}
	for i := 0; i < NUM; i++ {
		channels[i] <- 'A' + i
	}
	time.Sleep(time.Millisecond)
}

func main() {
	simpleChanDemo()
}
