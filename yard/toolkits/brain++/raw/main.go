package main

import (
	"fmt"
	"math/rand"

	"github.com/kataras/iris"
)

func genRandNum(row, col uint16) [][]string {
	mat := make([][]string, row)
	var i, j uint16 = 0, 0
	for i = 0; i < row; i++ {
		mat[i] = make([]string, col)
	}
	for i = 0; i < row; i++ {
		for j = 0; j < col; j++ {
			mat[i][j] = fmt.Sprintf("%02d", uint16(rand.Uint32()%100))
		}
	}
	return mat
}

func main() {
	app := iris.New()
	//app.Use(logger.New())

	htmlEngine := iris.HTML("./", ".html")
	htmlEngine.Reload(true)
	htmlEngine.AddFunc("label", func(offset int) string {
		return fmt.Sprintf("Row%02d: ", offset+1)
	})

	app.RegisterView(htmlEngine)

	app.Get("/", func(ctx iris.Context) {
		ctx.WriteString("Hello world! -- from iris.")
	})

	app.Get("/hello", func(ctx iris.Context) {
		ctx.ViewData("Title", "测试页面")
		ctx.ViewData("Content", "Hello world! -- from template")
		ctx.View("templates/index.html")
	})

	app.Get("/num", func(ctx iris.Context) {
		ctx.ViewData("Title", "随机数字")
		mat := genRandNum(25, 40)
		// fmt.Printf("%v\n", mat)
		ctx.ViewData("Mat", mat)
		ctx.ViewData("Content", "随机数字")
		ctx.View("templates/num.html")
	})

	app.Run(iris.Addr(":8080"), iris.WithCharset("UTF-8"))
}
