package ep

import (
	"bbxyard.com/msvc/go-kit/greeter/service"
	"github.com/go-kit/kit/endpoint"
	"github.com/go-kit/kit/log"
)

// Endpoints collects all of the endpoints that compose a greeter service. It's
// meant to be used as a helper struct, to collect all of the endpoints into a
// single parameter.
type Endpoints struct {
	HealthEndpoint   endpoint.Endpoint
	GreetingEndpoint endpoint.Endpoint
}

// MakeServerEndpoints returns service Endpoints, and wires in all the provided
// middlewares.
func MakeServerEndpoints(s service.Service, logger log.Logger) Endpoints {
	healthEndpoint, greetingEndpoint :=
		MakeHealthEndpoint(s), MakeGreetingEndpoint(s)
	healthEndpoint = LoggingMiddleware(log.With(logger, "method", "Health"))(healthEndpoint)
	greetingEndpoint = LoggingMiddleware(log.With(logger, "method", "Greeting"))(greetingEndpoint)
	return Endpoints{
		HealthEndpoint:   healthEndpoint,
		GreetingEndpoint: greetingEndpoint,
	}
}
