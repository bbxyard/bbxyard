package main

import "fmt"

type intSlice []int

func (s intSlice) Traverse() {
	fmt.Printf("%v, len=%d, cap=%d\n", s, len(s), cap(s))
}

func sliceOps() {
	fmt.Println("Creating slice")
	// var s intSlice // zero value for slice is nil
	s := make(intSlice, 0)

	for i := 0; i < 100; i++ {
		s.Traverse()
		s = append(s, 2*i+1)
	}
	fmt.Println(s)

	s1 := intSlice{2, 4, 6, 8, 10, 12}
	s1.Traverse()

	s2 := make(intSlice, 16)
	s3 := make(intSlice, 10, 32)
	s2.Traverse()
	s3.Traverse()

	fmt.Println("Copying slice")
	copy(s2, s1)
	s2.Traverse()

	fmt.Println("Deleting elements from slice")
	s2 = append(s2[:3], s2[4:]...)
	s2.Traverse()

	fmt.Println("Poping from front")
	front := s2[0]
	s2 = s2[1:]
	s2.Traverse()

	fmt.Println("Poping from back")
	tail := s2[len(s2)-1]
	s2 = s2[:len(s2)-1]
	s2.Traverse()

	fmt.Printf("front=%v, tail=%v\n", front, tail)
}
