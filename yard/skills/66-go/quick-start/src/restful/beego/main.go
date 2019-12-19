package main

import "github.com/astaxie/beego"

type UserController struct {
	beego.Controller
}

func (this *UserController) Get()  {
	this.Ctx.WriteString("<h1>Just do it</h1>")
}
func (this *UserController) Post()  {
	this.Ctx.WriteString("User.Post: Enjoy it.")
}

func main() {
	// RESTful Controller 路由
	beego.Router("/user", &UserController{})

	beego.Run(":18081")
}
