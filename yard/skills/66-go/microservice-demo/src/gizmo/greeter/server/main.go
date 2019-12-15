package main

import (
	"flag"
	"os"
	"strconv"

	"bbxyard.com/msvc/gizmo/greeter/ep"
	"bbxyard.com/msvc/gizmo/greeter/sd"
	"bbxyard.com/msvc/gizmo/greeter/service"
	"github.com/oklog/oklog/pkg/group"

	"bbxyard.com/msvc/abc/yvhai/cmd"
	"bbxyard.com/msvc/gizmo/greeter/dto"
	"github.com/NYTimes/gizmo/config"
	"github.com/NYTimes/gizmo/server"
)

func main() {
	fs := flag.NewFlagSet("greetersvc", flag.ExitOnError)
	var (
		configPath = fs.String("config.path", "../conf/gizmo-server.json", "Config file path")
		consulHost = fs.String("consul.host", "", "Consul Host")
		consulPort = fs.String("consul.port", "8500", "Consul Port")
	)
	fs.Usage = cmd.UsageFor(fs, os.Args[0]+" [flags]")
	fs.Parse(os.Args[1:])

	var cfg *dto.Config
	config.LoadJSONFile(*configPath, &cfg)
	server.Init("gizmo-server", cfg.Server)

	var (
		svc       = service.GreeterService{}
		endpoints = ep.MakeServerEndpoints(svc)
		registrar = sd.ConsulRegister(*consulHost, *consulPort, "", strconv.Itoa(cfg.Server.HTTPPort))
	)

	var g group.Group
	{
		err := server.Register(dto.NewTService(cfg, endpoints))
		if err != nil {
			server.Log.Fatal("unable to register service:", err)
			os.Exit(1)
		}

		g.Add(func() error {
			registrar.Register()
			return server.Run()
		}, func(err error) {
			registrar.Deregister()
			server.Log.Fatal("server encountered a fatal error:", err)
		})
	}
	cmd.AddSystemSignalHook(g)
	server.Log.Debug("exit", g.Run())
}
