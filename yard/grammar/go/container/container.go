package container

type Traversal interface {
	Traverse()
	Name() string
}
