package main

import (
	"github.com/kataras/iris/context"
	"github.com/kataras/iris"
)

type clientPage struct {
	Title string
	Desc  string
}

func main() {
	app := iris.New()
	app.Logger().SetLevel("debug")
	app.RegisterView(iris.HTML("./templates", ".html")) // select the html engine to serve templates

	// 根路由
	app.Handle("GET", "/", func(ctx context.Context) {
		ctx.HTML("<h1>Hallo Welt - aus Iris</h1>")
	})

	// 更多路由
	regRouter(app)

	app.Favicon("../static/favicons/favicon.ico")
	app.Run(iris.Addr(":18094"), iris.WithoutServerError(iris.ErrServerClosed))
}

// 注册路由
func regRouter(app *iris.Application)  {
	app.Get("/json", func(ctx iris.Context) {
		ctx.JSON(iris.Map{"name": "iris", "date": "20191220"})
	})
	app.Get("/hallo", func(ctx iris.Context) {
		ctx.ViewData("Title", "这是标题")
		ctx.ViewData("Desc", "这是描述信息")
		ctx.View("index.html")
		// ctx.View("index.html", clientPage{"这是标题", "这是描述信息"})
	})
}
