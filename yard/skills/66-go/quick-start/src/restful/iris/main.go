package main

import (
	"github.com/kataras/iris"
)

// RESTful Get
func userInfoGet(ctx iris.Context) {
	ctx.HTML("[iris]userInfoGet: Hello World.\n")
}

// RESTful Post
func userInfoPost(ctx iris.Context) {
	ctx.HTML("[iris]userInfoPost: Hello World.\n")
}

// RESTful Json
func jsonOutput(ctx iris.Context) {
	ctx.JSON(iris.Map{"name": "iris", "date": "20191220"})
}

// 路由注册
func regRouter(app *iris.Application) {
	// RESTful 路由
	app.Handle("GET", "/user", userInfoGet)
	app.Handle("POST", "/user", userInfoPost)
	app.Post("/json", jsonOutput)
}

func main() {
	app := iris.New()
	app.Logger().SetLevel("debug")

	// 根路由
	app.Handle("GET", "/", func(ctx iris.Context) {
		ctx.HTML("<h1>Hallo Welt - aus Iris</h1>")
	})

	// 更多路由
	regRouter(app)

	app.Run(iris.Addr(":18084"), iris.WithoutServerError(iris.ErrServerClosed))
}
