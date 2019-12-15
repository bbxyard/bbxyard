package ep

import (
	"net/http"

	"bbxyard.com/msvc/gizmo/greeter/service"
	"github.com/NYTimes/gizmo/server"
	"golang.org/x/net/context"
)

// MakeGreetingEndpoint constructs a Greeting endpoint.
func MakeGreetingEndpoint(s service.Service) server.JSONContextEndpoint {
	return func(ctx context.Context, request *http.Request) (i int, i2 interface{}, err error) {
		vars := request.URL.Query()
		names, exists := vars["name"]
		if !exists || len(names) != 1 {
			return http.StatusBadRequest, errorResponse{Error: "query parameter 'name' required"}, nil
		}
		greeting := s.Greeting(names[0])
		return http.StatusOK, GreetingResponse{Greeting: greeting}, nil
	}
}

// GreetingRequest collects the requests values for the Greeting method.
type GreetingRequest struct {
	Name string `json:"name,omitempty"`
}

// GreetingResponse collects the response values for the Greeting method.
type GreetingResponse struct {
	Greeting string `json:"greeting,omitempty"`
}
