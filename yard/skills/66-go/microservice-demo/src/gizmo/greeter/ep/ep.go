package ep

import (
	"bbxyard.com/msvc/gizmo/greeter/service"
	"github.com/NYTimes/gizmo/server"
)

// Endpoints collects all of the endpoints that compose a greeter service.
type EndPoints struct {
	HealthEndpoint   server.JSONContextEndpoint
	GreetingEndpoint server.JSONContextEndpoint
}

// MakeServerEndpoints returns service Endpoints.
func MakeServerEndpoints(s service.Service) EndPoints {
	return EndPoints{
		HealthEndpoint:   MakeHealthEndpoint(s),
		GreetingEndpoint: MakeGreetingEndpoint(s),
	}
}

type errorResponse struct {
	Error string `json:"error"`
}
