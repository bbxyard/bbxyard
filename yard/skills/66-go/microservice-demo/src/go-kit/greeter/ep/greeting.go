package ep

import (
	"bbxyard.com/msvc/go-kit/greeter/service"
	"github.com/go-kit/kit/endpoint"
	"golang.org/x/net/context"
)

// GreetingRequest collects the request parameters for the Greeting method.
type GreetingRequest struct {
	Name string `json:"name,omitempty"`
}

// GreetingResponse collects the response values for the Greeting method.
type GreetingResponse struct {
	Greeting string `json:"greeting,omitempty"`
	Err      error  `json:"err,omitempty"`
}

// Failed implements Failer.
func (r GreetingResponse) Failed() error {
	return r.Err
}

// MakeGreetingEndpoint constructs a Greeter endpoint wrapping the service.
func MakeGreetingEndpoint(s service.Service) endpoint.Endpoint {
	return func(ctx context.Context, request interface{}) (response interface{}, err error) {
		req := request.(GreetingRequest)
		greeting := s.Greeting(req.Name)
		return GreetingResponse{Greeting: greeting}, nil
	}
}
