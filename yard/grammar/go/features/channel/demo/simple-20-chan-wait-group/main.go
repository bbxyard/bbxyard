package main

import (
	"fmt"
	"sync"
)

const NUM = 10

func doWork(workerId int, c chan int, wg *sync.WaitGroup) {
	for n := range c {
		fmt.Printf("Worker %d handled %c\n", workerId, n)
		wg.Done()
	}
}

type Worker struct {
	in chan int
	wg *sync.WaitGroup
}

func createWorker(workerId int, wg *sync.WaitGroup) Worker {
	w := Worker{
		in: make(chan int),
		wg: wg,
	}
	go doWork(workerId, w.in, w.wg)
	return w
}

func simpleChanDemo() {
	var workers [NUM]Worker
	wg := &sync.WaitGroup{}
	for i, _ := range workers {
		workers[i] = createWorker(i, wg)
	}

	wg.Add(NUM * 2)
	for i, worker := range workers {
		worker.in <- 'a' + i
	}
	for i, worker := range workers {
		worker.in <- 'A' + i
	}
	// wait for all
	fmt.Println("wait task all")
	wg.Wait()
}

func main() {
	simpleChanDemo()
}
