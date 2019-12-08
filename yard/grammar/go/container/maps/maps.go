package main

import (
	"fmt"
	"go/study/container"
)

type str2strMap map[string]string
type str2intMap map[string]int

func (m *str2strMap) Traverse() {
	for k, v := range *m {
		fmt.Println(k, v)
	}
}
func (m *str2strMap) Name() string {
	return "str2strMap"
}

func (m *str2intMap) Traverse() {
	for k, v := range *m {
		fmt.Println(k, v)
	}
}
func (m *str2intMap) Name() string {
	return "str2intMap"
}

func main() {

	m := str2strMap{
		"name":    "ccmouse",
		"course":  "golang",
		"site":    "imooc",
		"quality": "not-bad",
	}

	m2 := make(str2intMap) // m2 == empty map
	var m3 str2intMap      // m3 == nil

	fmt.Println("m, m2, m3:")
	fmt.Println(m, m2, m3)

	marr := [...]container.Traversal{&m, &m2, &m3}
	for _, t := range marr {
		fmt.Println("Traversing map: ", t.Name())
		t.Traverse()
	}

	fmt.Println("Getting values")
	courseName := m["course"]
	fmt.Println(`m["course"] =`, courseName)
	if causeName, ok := m["cause"]; ok {
		fmt.Println(causeName)
	} else {
		fmt.Println("key 'cause' does not exist")
	}

	fmt.Println("Deleting values")
	name, ok := m["name"]
	fmt.Printf("m[%q] before delete: %q, %v\n", "name", name, ok)

	delete(m, "name")
	name, ok = m["name"]
	fmt.Printf("m[%q] after delete: %q, %v\n", "name", name, ok)
}
