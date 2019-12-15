package service

// Service describe greetings service.
type Service interface {
	Health() bool
	Greeting(name string) string
}

// GreeterService implementation of the service interface.
type GreeterService struct {
}

// Health implementation of the Service.
func (GreeterService) Health() bool {
	return true
}

// Greeting implementation of the Service.
func (GreeterService) Greeting(name string) string {
	greeting := "GO-KIT hello " + name
	return greeting
}
