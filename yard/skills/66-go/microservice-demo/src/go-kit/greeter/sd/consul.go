package sd

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"

	"github.com/go-kit/kit/log"
	"github.com/go-kit/kit/sd"
	consulsd "github.com/go-kit/kit/sd/consul"
	"github.com/hashicorp/consul/api"
)

// ConsulRegister method.
func ConsulRegister(consulHost string,
	consulPort string,
	advertiseHost string,
	advertisePort string) (registrar sd.Registrar) {

	// Logging domain.
	var logger log.Logger
	{
		logger = log.NewLogfmtLogger(os.Stderr)
		logger = log.With(logger, "ts", log.DefaultTimestampUTC)
		logger = log.With(logger, "caller", log.DefaultCaller)
	}

	rand.Seed(time.Now().UTC().UnixNano())

	// Service discovery domain. In this example we use consul.
	var client consulsd.Client
	{
		consulConfig := api.DefaultConfig()
		fmt.Sprintf(consulConfig.Address, "%s:%s", consulHost, consulPort)
		consulClint, err := api.NewClient(consulConfig)
		if err != nil {
			logger.Log("err", err)
			os.Exit(1)
		}
		client = consulsd.NewClient(consulClint)
	}

	check := api.AgentServiceCheck{
		HTTP:     "http://" + advertiseHost + ":" + advertisePort + "/health",
		Interval: "10s",
		Timeout:  "1s",
		Notes:    "Basic health checks",
	}

	port, _ := strconv.Atoi(advertisePort)
	num := rand.Intn(100) // to make service ID unique
	asr := api.AgentServiceRegistration{
		ID:      "go-kit-serv-greeter-" + strconv.Itoa(num), // unique service ID
		Name:    "go-kit-serv-greeter",
		Tags:    []string{"go-kit", "greeter"},
		Address: advertiseHost,
		Port:    port,
		Check:   &check,
	}

	registrar = consulsd.NewRegistrar(client, &asr, logger)
	return
}
