package ds

type ElemType int

type MyQueue interface {
	Push(v ElemType)
	Pop() ElemType
	IsEmpty() bool
}
