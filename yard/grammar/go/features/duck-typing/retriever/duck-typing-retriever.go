package main

import (
	"fmt"
	"go/study/features/duck-typing/retriever/api"
	"go/study/features/duck-typing/retriever/mock"
	real2 "go/study/features/duck-typing/retriever/real"
	"time"
)

const url = "https://www.imooc.com"

func download(r api.Retriever) string {
	return r.Get(url)
}

func post(poster api.Poster) {
	poster.Post(url,
		map[string]string{
			"name":   "ccmouse",
			"course": "golang",
		})
}

func session(s api.RetrieverPoster) string {
	s.Post(url, map[string]string{
		"contents": "another faked imooc.com",
	})
	return s.Get(url)
}

func inspect(r api.Retriever) {
	fmt.Println("Inspecting: ", r)
	fmt.Printf(" > Type: %T value: %v\n", r, r)
	fmt.Print(" > Type switch: ")
	switch v := r.(type) {
	case *mock.Retriever:
		fmt.Println("Contents: ", v.Contents)
	case *real2.Retriever:
		fmt.Println("UserAgent: ", v.UserAgent)
	}
	fmt.Println()
}

func main() {
	var r api.Retriever
	mockRetriever := mock.Retriever{Contents: "this is a fake imooc.com"}
	r = &mockRetriever
	inspect(r)

	r = &real2.Retriever{
		UserAgent: "Mozilla/5.0",
		TimeOut:   time.Minute,
	}
	inspect(r)

	// Type assertion
	if mockRetriever, ok := r.(*mock.Retriever); ok {
		fmt.Println(mockRetriever.Contents)
	} else {
		fmt.Println("r is not a mock retriever")
	}

	fmt.Println("Try a session with mockRetriever")
	fmt.Println(session(&mockRetriever))
}
