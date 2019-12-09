package main

import (
	"go/study/00/errhandler/file-listing-server/handlers"
	"log"
	"net/http"
	"os"
)

// Inner 返回错误
// Final 统一处理错误
type AppInnerHandler func(writer http.ResponseWriter, request *http.Request) error
type AppFinalHandler func(writer http.ResponseWriter, request *http.Request)

type UserError interface {
	error
	Message() string
}

func recoverError(writer http.ResponseWriter) {
	if r := recover(); r != nil {
		log.Printf("Panic: %v", r)
		http.Error(writer,
			http.StatusText(http.StatusInternalServerError),
			http.StatusInternalServerError)
	}
}

func handleError(writer http.ResponseWriter, err error) {
	if err != nil {
		log.Printf("Error occured "+
			"handling request: %s",
			err.Error())

		// user error
		if userErr, ok := err.(UserError); ok {
			http.Error(writer,
				userErr.Message(),
				http.StatusBadRequest)
			return
		}

		// system error
		code := http.StatusOK
		switch {
		case os.IsNotExist(err):
			code = http.StatusNotFound
		case os.IsPermission(err):
			code = http.StatusForbidden
		default:
			code = http.StatusInternalServerError
		}
		http.Error(writer, http.StatusText(code), code)
	}

}

// 错误统一处理
func errWrapper(handler AppInnerHandler) AppFinalHandler {
	return func(writer http.ResponseWriter, request *http.Request) {
		// #1. 恢复错误
		defer recoverError(writer)
		// #2. 执行核心处理
		err := handler(writer, request)
		// #3. 错误处理
		handleError(writer, err)
	}
}

func main() {
	http.HandleFunc("/", errWrapper(handlers.HandleFileList))

	err := http.ListenAndServe(":8888", nil)
	if err != nil {
		panic(err)
	}
}
