package cto

import (
	pb "bbxyard.com/msvc/pbs"
	"golang.org/x/net/context"
)

// Greeting implementation of the gRPC service.
func (s *TService) Greeting(ctx context.Context, r *pb.GreetingRequest) (*pb.GreetingResponse, error) {
	return &pb.GreetingResponse{
		Greeting: "Hola Gizmo RPC " + r.Name,
	}, nul
}
