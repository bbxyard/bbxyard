package dto

import (
	"encoding/json"
	"errors"
	"net/http"

	"github.com/go-kit/kit/transport"
	"github.com/gorilla/mux"

	"github.com/go-kit/kit/log"

	"bbxyard.com/msvc/go-kit/greeter/ep"

	transport_http "github.com/go-kit/kit/transport/http"
	"golang.org/x/net/context"
)

var (
	// ErrBadRouting is returned when an expected path variable is missing
	ErrBadRouting = errors.New("inconsistent mapping between route and handler")
)

// NewHTTPHandler returns an HTTP handler that makes a set of endpoints
// available on predefined paths.
func NewHTTPHandler(endpoints ep.Endpoints, logger log.Logger) http.Handler {
	m := mux.NewRouter()
	options := []transport_http.ServerOption{
		transport_http.ServerErrorEncoder(encodeError),
		transport_http.ServerErrorHandler(transport.NewLogErrorHandler(logger)),
	}

	// GET /health         retrieves service heath information
	// GET /greeting?name  retrieves greeting

	m.Methods("GET").Path("/health").Handler(transport_http.NewServer(
		endpoints.HealthEndpoint,
		DecodeHTTPGreetingRequest,
		EncodeHTTPGenericResponse,
		options...,
	))
	m.Methods("GET").Path("/greeting").Handler(transport_http.NewServer(
		endpoints.GreetingEndpoint,
		DecodeHTTPGreetingRequest,
		EncodeHTTPGenericResponse,
		options...,
	))

	return m
}

// DecodeHTTPHealthRequest method.
func DecodeHTTPHealthRequest(_ context.Context, r *http.Request) (interface{}, error) {
	return ep.HealthRequest{}, nil
}

// DecodeHTTPGreetingRequest method.
func DecodeHTTPGreetingRequest(_ context.Context, r *http.Request) (interface{}, error) {
	vars := r.URL.Query()
	names, exists := vars["name"]
	if !exists || len(names) != 1 {
		return nil, ErrBadRouting
	}
	req := ep.GreetingRequest{Name: names[0]}
	return req, nil
}

func encodeError(_ context.Context, err error, w http.ResponseWriter) {
	w.WriteHeader(err2code(err))
	json.NewEncoder(w).Encode(errorWrapper{Error: err.Error()})
}

func err2code(err error) int {
	switch err {
	default:
		return http.StatusInternalServerError
	}
}

type errorWrapper struct {
	Error string `json:"error"`
}

// EncodeHTTPGenericResponse is a transport/http.EncodeResponseFunc that encodes
// the response as JSON to the response writer
func EncodeHTTPGenericResponse(ctx context.Context, w http.ResponseWriter, response interface{}) error {
	if f, ok := response.(ep.Failer); ok && f.Failed() != nil {
		encodeError(ctx, f.Failed(), w)
		return nil
	}
	w.Header().Set("Content-Type", "application/json; charset=utf-8")
	return json.NewEncoder(w).Encode(response)
}
