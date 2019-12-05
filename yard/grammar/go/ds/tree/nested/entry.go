package main

import (
	"fmt"
	"go/study/ds/tree"
)

type MyTreeNode struct {
	*tree.Node
}

func (myNode *MyTreeNode) postOrder() {
	if myNode == nil || myNode.Node == nil {
		return
	}

	left := MyTreeNode{myNode.Left}
	right := MyTreeNode{myNode.Right}

	left.postOrder()
	right.postOrder()
	myNode.Print()
}

func main() {
	root := MyTreeNode{&tree.Node{Value: 3}}
	root.Left = &tree.Node{}
	root.Right = &tree.Node{5, nil, nil}
	root.Right.Left = new(tree.Node)
	root.Left.Right = tree.CreateNode(2)
	root.Right.Left.SetValue(4)

	fmt.Print("In-order traversal: ")
	root.Traverse()

	fmt.Print("My own post-order traversal: ")
	root.postOrder()
	fmt.Println()
}
