package queue

import "go/study/ds"

type Queue []ds.ElemType

// Pushes the element into the queue.
//		e.g. q.Push(1235)
func (q *Queue) Push(v ds.ElemType) {
	*q = append(*q, v)
}

// Pops element from head.
func (q *Queue) Pop() ds.ElemType {
	head := (*q)[0]
	*q = (*q)[1:]
	return head
}

// Returns if the queue is Empty or not.
func (q *Queue) IsEmpty() bool {
	return len(*q) == 0
}
