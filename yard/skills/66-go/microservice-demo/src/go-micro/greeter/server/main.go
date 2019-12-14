package main

import (
	"context"
	"log"

	"github.com/micro/go-micro"

	pb "bbxyard.com/msvc/pbs"
)

// Greeter implements greeter service.
type Greeter struct {
}

// Greeting method implementation.
func (g *Greeter) Greeting(ctx context.Context, in *pb.GreetingRequest, out *pb.GreetingResponse) error {
	out.Greeting = "GO-MIRCO Hello " + in.Name
	return nil
}

func main() {
	service := micro.NewService(
		micro.Name("go-micro-serv-greeter"),
		micro.Version("latest"))

	service.Init()

	pb.RegisterGreeterHandler(service.Server(), new(Greeter))

	if err := service.Run(); err != nil {
		log.Fatal(err)
	}
}
