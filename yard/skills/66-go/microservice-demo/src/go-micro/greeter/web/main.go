package main

import (
	"context"
	"encoding/json"
	"log"
	"net/http"

	pb "bbxyard.com/msvc/pbs"
	"github.com/micro/go-micro/client"
	"github.com/micro/go-micro/web"
)

func main() {
	service := web.NewService(web.Name("go-micro-web-greeter"))

	service.HandleFunc("/greeting", func(w http.ResponseWriter, r *http.Request) {
		if r.Method == "GET" {
			var name string
			vars := r.URL.Query()
			names, exists := vars["name"]
			if !exists || len(names) != 1 {
				name = ""
			} else {
				name = names[0]
			}

			theClient := pb.NewGreeterService("go-micro-serv-greeter", client.DefaultClient)
			resp, err := theClient.Greeting(context.Background(), &pb.GreetingRequest{Name: name})
			if err != nil {
				http.Error(w, err.Error(), 500)
				return
			}

			jsonStr, err := json.Marshal(resp)
			if err != nil {
				http.Error(w, err.Error(), http.StatusInternalServerError)
				return
			}

			w.Header().Set("Content-Type", "application/json")
			w.Write(jsonStr)
			return
		}
	})

	if err := service.Init(); err != nil {
		log.Fatal(err)
	}

	if err := service.Run(); err != nil {
		log.Fatal(err)
	}
}
