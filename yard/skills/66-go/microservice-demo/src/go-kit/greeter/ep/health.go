package ep

import (
	"bbxyard.com/msvc/go-kit/greeter/service"
	"github.com/go-kit/kit/endpoint"
	"golang.org/x/net/context"
)

// HealthRequest collects the request parameters for the Health method.
type HealthRequest struct {
}

// HealthResponse collects the response values for the Health method.
type HealthResponse struct {
	Healthy bool  `json:"healthy,omitempty"`
	Err     error `json:"err,omitempty"`
}

// Failed implements Failer.
func (r HealthResponse) Failed() error {
	return r.Err
}

// MakeHealthEndpoint constructs a Health endpoint wrapping the service.
func MakeHealthEndpoint(s service.Service) endpoint.Endpoint {
	return func(ctx context.Context, request interface{}) (response interface{}, err error) {
		healthy := s.Health()
		return HealthResponse{Healthy: healthy}, nil
	}
}
