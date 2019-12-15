package cto

import (
	"github.com/NYTimes/gizmo/server"
)

type (

	// Config is a struct to contain all the needed.
	// configuration for our JSONService.
	Config struct {
		Server *server.Config
	}
)
