package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
)

// RESTful Get
func userInfoGet(c *gin.Context)  {
	c.String(http.StatusOK, "[gin]userInfoGet: Hello World.\n")
}

// RESTful Post
func userInfoPost(c *gin.Context)  {
	c.String(http.StatusOK, "[gin]userInfoPost: Hello World.\n")
}

// 提取path中参数
func userGetById(c *gin.Context)  {
	id := c.Param("id")
	c.String(http.StatusOK, fmt.Sprintf("id is: %s\n", id))
}

// 组路由
func action1(c *gin.Context)  {
	c.String(http.StatusOK, "action1\n")
}
func action2(c *gin.Context)  {
	c.String(http.StatusOK, "action2\n")
}
func action3(c *gin.Context)  {
	c.String(http.StatusOK, "action3\n")
}

func main() {
	router := gin.Default()

	// RESTful路由
	router.GET("/user", userInfoGet)
	router.POST("/user", userInfoPost)

	// 不支持正则路由
	// 提取path中的参数
	router.GET("/users/:id", userGetById)

	// 组路由
	group1 := router.Group("/g1")
	{
		group1.GET("/action1", action1)
		group1.GET("/action2", action2)
		group1.GET("/action3", action3)
	}

	// 服务启动
	router.Run(":18082")
}
