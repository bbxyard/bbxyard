package main

import (
	"flag"
	"fmt"
	"os"

	"bbxyard.com/msvc/abc/yvhai/cmd"

	pb "bbxyard.com/msvc/pbs"
	"golang.org/x/net/context"

	"google.golang.org/grpc"
)

func main() {
	fs := flag.NewFlagSet("greeter-client", flag.ExitOnError)
	var (
		serviceAddr = fs.String("service.addr", "127.0.0.1:9220", "The Gizmo greeter service address")
		name        = fs.String("name", "gizmo RPC call", "The Name to greet")
	)
	fmt.Printf("%v", fs)
	fs.Usage = cmd.UsageFor(fs, os.Args[0]+" [flags]")
	fs.Parse(os.Args[1:])

	conn, err := grpc.Dial(*serviceAddr, grpc.WithInsecure())
	if err != nil {
		fmt.Println("grpc gizmo connection error:", err)
		os.Exit(1)
	}
	defer func() {
		err := conn.Close()
		if err != nil {
			fmt.Println("grpcGizmoConnectionError", err)
		}
	}()

	client := pb.NewGreeterClient(conn)
	resp, err := client.Greeting(context.Background(), &pb.GreetingRequest{Name: *name})
	if err != nil {
		fmt.Println("gizmoServiceErr", err)
		return
	}
	fmt.Println("gizmoServiceResponse:", resp.Greeting)
}
