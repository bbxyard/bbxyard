package main

import (
	"fmt"
	"github.com/kataras/iris"
)

type clientPage struct {
	Title string
	Desc  string
}

func main() {
	fmt.Println("Just do it")
	app := iris.New()
	app.Logger().SetLevel("debug")
	app.RegisterView(iris.HTML("./templates", ".html")) // select the html engine to serve templates

	app.Handle("GET", "/", func(ctx iris.Context) {
		ctx.HTML("<h1>Hallo Welt - aus Iris</h1>")
	})

	app.Get("/json", func(ctx iris.Context) {
		ctx.JSON(iris.Map{"name": "iris", "date": "20190830"})
	})

	app.Get("/hallo", func(ctx iris.Context) {
		ctx.ViewData("Title", "这是标题")
		ctx.ViewData("Desc", "这是描述信息")
		ctx.View("index.html")
		// ctx.View("index.html", clientPage{"这是标题", "这是描述信息"})
	})

	app.Favicon("./static/favicons/favicon.ico")

	app.Run(iris.Addr(":8080"), iris.WithoutServerError(iris.ErrServerClosed))
}
