package main

import (
	"fmt"
)

const NUM = 10

func doWork(workerId int, c chan int, done chan bool) {
	for n := range c {
		fmt.Printf("Worker %d handled %c\n", workerId, n)
		go func() {
			done <- true
		}()
	}
}

type Worker struct {
	in   chan int
	done chan bool
}

func createWorker(workerId int) Worker {
	w := Worker{
		in:   make(chan int),
		done: make(chan bool),
	}
	go doWork(workerId, w.in, w.done)
	return w
}

func simpleChanDemo() {
	var workers [NUM]Worker
	for i, _ := range workers {
		workers[i] = createWorker(i)
	}
	for i, worker := range workers {
		worker.in <- 'a' + i
	}
	for i, worker := range workers {
		worker.in <- 'A' + i
	}
	// wait for all
	fmt.Println("wait task all")
	for _, worker := range workers {
		<-worker.done
		<-worker.done
	}
}

func main() {
	simpleChanDemo()
}
