package main

import (
	"math/rand"
	"time"

	"github.com/gin-gonic/gin"
	"go.uber.org/zap"
)

const KeyRequestId = "requestId"

func main() {
	r := gin.Default()
	logger, err := zap.NewProduction()
	if err != nil {
		panic(err)
	}

	r.Use(func(c *gin.Context) {
		s := time.Now()
		c.Next()
		logger.Info("incoming request",
			zap.String("path", c.Request.URL.Path),
			zap.Int("status", c.Writer.Status()),
			zap.Duration("elapsed", time.Now().Sub(s)))
	}, func(c *gin.Context) {
		c.Set(KeyRequestId, rand.Uint64())
		c.Next()
	})

	r.GET("/ping", func(c *gin.Context) {
		h := gin.H{
			"message": "pong",
		}
		if rid, exists := c.Get(KeyRequestId); exists {
			h[KeyRequestId] = rid
		}
		c.JSON(200, h)
	})

	r.GET("/hallo", func(c *gin.Context) {
		c.String(200, "hallo Golang")
	})

	logger.Info("Gin Server Started.")

	r.Run(":8888")
}
