package main

import (
	"go/study/1024-rpc/jsonrpc/api"
	"log"
	"net"
	"net/rpc"
	"net/rpc/jsonrpc"
)

func main() {
	rpc.Register(api.MathService{})
	listener, err := net.Listen("tcp", ":1234")
	if err != nil {
		panic(err)
	}
	log.Println("rpc-server listened at: ", listener.Addr().String())

	for {
		conn, err := listener.Accept()
		if err != nil {
			log.Printf("accept error: %v", err)
			continue
		}

		log.Println("accept: ", conn)
		go jsonrpc.ServeConn(conn)
	}
}
