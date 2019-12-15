package pb

import "google.golang.org/grpc"

func GetGreeterServiceDesc() *grpc.ServiceDesc {
	return &_Greeter_serviceDesc
}
