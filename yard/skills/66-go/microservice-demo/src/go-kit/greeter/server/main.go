package main

import (
	"flag"
	"fmt"
	"net"
	"net/http"
	"os"
	"os/signal"
	"syscall"

	pb "bbxyard.com/msvc/pbs"
	"google.golang.org/grpc"

	"github.com/oklog/oklog/pkg/group"

	"bbxyard.com/msvc/abc/yvhai/cmd"
	"bbxyard.com/msvc/go-kit/greeter/dto"
	"bbxyard.com/msvc/go-kit/greeter/ep"
	"bbxyard.com/msvc/go-kit/greeter/sd"
	"bbxyard.com/msvc/go-kit/greeter/service"
	"github.com/go-kit/kit/log"
)

func main() {
	fs := flag.NewFlagSet("greetersvc", flag.ExitOnError)
	var (
		debugAddr  = fs.String("debug.addr", ":9100", "Debug and metrics listen address")
		consulAddr = fs.String("consul.addr", "", "Consul Address")
		consulPort = fs.String("consul.port", "8500", "Consul Port")
		httpAddr   = fs.String("http.addr", "", "HTTP Listen Address")
		httpPort   = fs.String("http.port", "9110", "HTTP Listen Port")
		grpcAddr   = fs.String("grpc-addr", ":9120", "gRPC listen address")
	)
	fs.Usage = cmd.UsageFor(fs, os.Args[0]+" [flags]")
	fs.Parse(os.Args[1:])

	var logger log.Logger
	{
		logger = log.NewLogfmtLogger(os.Stderr)
		logger = log.With(logger, "ts", log.DefaultTimestampUTC)
		logger = log.With(logger, "caller", log.DefaultCaller)
	}

	var svc service.Service
	{
		svc = service.GreeterService{}
		svc = service.LoggingMiddleware(logger)(svc)
	}

	var (
		endpoints   = ep.MakeServerEndpoints(svc, logger)
		httpHandler = dto.NewHTTPHandler(endpoints, logger)
		registrar   = sd.ConsulRegister(*consulAddr, *consulPort, *httpAddr, *httpPort)
		grpcServer  = dto.NewGRPCServer(endpoints, logger)
	)

	var g group.Group
	{
		// The debug listener mounts the http.DefaultServeMux, and serves up
		// stuff like the Go debug and profiling routes, and so on.
		debugListener, err := net.Listen("tcp", *debugAddr)
		if err != nil {
			logger.Log("transport", "debug/HTTP", "during", "Listen", "err", err)
			os.Exit(1)
		}
		g.Add(func() error {
			logger.Log("transport", "debug/HTTP", "addr", *debugAddr)
			return http.Serve(debugListener, http.DefaultServeMux)
		}, func(err error) {
			debugListener.Close()
		})
	}

	{
		// The service discovery registration.
		g.Add(func() error {
			logger.Log("transport", "HTTP", "addr", *httpAddr, "port", *httpPort)
			registrar.Register()
			return http.ListenAndServe(":"+*httpPort, httpHandler)
		}, func(err error) {
			registrar.Deregister()
		})
	}

	{
		// The gRPC listener mounts the Go kit gRPC server we created.
		grpcListener, err := net.Listen("tcp", *grpcAddr)
		if err != nil {
			logger.Log("transport", "gRPC", "during", "Listen", "err", err)
			os.Exit(1)
		}
		g.Add(func() error {
			logger.Log("transport", "gRPC", "addr", *grpcAddr)
			baseServer := grpc.NewServer()
			pb.RegisterGreeterServer(baseServer, grpcServer)
			return baseServer.Serve(grpcListener)
		}, func(err error) {
			grpcListener.Close()
		})
	}

	{
		// This function just sits and waits for ctrl-c
		cancelInterrupt := make(chan struct{})
		g.Add(func() error {
			c := make(chan os.Signal, 1)
			signal.Notify(c, syscall.SIGINT, syscall.SIGTERM)
			select {
			case sig := <-c:
				return fmt.Errorf("received signal %s", sig)
			case <-cancelInterrupt:
				return nil
			}
		}, func(err error) {
			close(cancelInterrupt)
		})
	}

	logger.Log("exit", g.Run())
}
