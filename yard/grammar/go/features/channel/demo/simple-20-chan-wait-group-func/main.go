package main

import (
	"fmt"
	"sync"
)

const NUM = 10

func doWork(workerId int, w Worker) {
	for n := range w.in {
		fmt.Printf("Worker %d handled %c\n", workerId, n)
		w.done()
	}
}

type Worker struct {
	in   chan int
	done func()
}

func createWorker(workerId int, wg *sync.WaitGroup) Worker {
	w := Worker{
		in: make(chan int),
		done: func() {
			wg.Done()
		},
	}
	go doWork(workerId, w)
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
