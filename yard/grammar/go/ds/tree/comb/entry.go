package main

import (
	"fmt"
	"go/study/ds/tree"
)

type MyTreeNode struct {
	rep *tree.Node
}

func (myNode *MyTreeNode) postOrder() {
	if myNode == nil || myNode.rep == nil {
		return
	}

	left := MyTreeNode{myNode.rep.Left}
	right := MyTreeNode{myNode.rep.Right}

	left.postOrder()
	right.postOrder()
	myNode.rep.Print()
}

func main() {
	var root tree.Node

	root = tree.Node{Value: 3}
	root.Left = &tree.Node{}
	root.Right = &tree.Node{5, nil, nil}
	root.Right.Left = new(tree.Node)
	root.Left.Right = tree.CreateNode(2)
	root.Right.Left.SetValue(4)

	fmt.Println("In-order traversal: ")
	root.Traverse()

	fmt.Println("My own post-order traversal: ")
	myRoot := MyTreeNode{&root}
	myRoot.postOrder()
	fmt.Println()

	nodeCount := 0
	root.TraverseFunc(func(node *tree.Node) {
		nodeCount++
	})
	fmt.Println("Node count: ", nodeCount)

	c := root.TraverseWithChannel()
	maxNodeValue := 0
	for node := range c {
		if node.Value > maxNodeValue {
			maxNodeValue = node.Value
		}
	}
	fmt.Println("Max node value: ", maxNodeValue)
}
