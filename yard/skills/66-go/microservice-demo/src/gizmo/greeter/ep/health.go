package ep

import (
	"net/http"

	"bbxyard.com/msvc/gizmo/greeter/service"
	"github.com/NYTimes/gizmo/server"
	"golang.org/x/net/context"
)

// MakeHealthEndpoint constructs a Health endpoint.
func MakeHealthEndpoint(s service.Service) server.JSONContextEndpoint {
	return func(ctx context.Context, request *http.Request) (int, interface{}, error) {
		healthy := s.Health()
		return http.StatusOK, HealthResponse{Healthy: healthy}, nil
	}
}

// HealthRequest collects the requests values for the Health method.
type HealthRequest struct {
}

// HealthResponse collects the response values for the Health method.
type HealthResponse struct {
	Healthy bool `json:"healthy,omitempty"`
}
