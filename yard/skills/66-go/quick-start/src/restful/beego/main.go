package main

import (
	"fmt"
	"github.com/astaxie/beego"
)

// RESTful 路由
type UserController struct {
	beego.Controller
}

func (this *UserController) Get()  {
	this.Ctx.WriteString("<h1>Just do it</h1>")
}
func (this *UserController) Post()  {
	this.Ctx.WriteString("User.Post: Enjoy it.")
}

// 正则路由
type RegExpController struct {
	beego.Controller
}

func (this *RegExpController) Get()  {
	this.Ctx.WriteString("In RegExp Mode!")
	id := this.Ctx.Input.Param(":id")
	if len(id) > 0 {
		this.Ctx.WriteString(fmt.Sprintf("id is: %s\n", id))
	}
	splat := this.Ctx.Input.Param(":splat")
	if len(splat) > 0 {
		this.Ctx.WriteString(fmt.Sprintf("splat is: %s\n", splat))
	}
	path := this.Ctx.Input.Param(":path")
	if len(path) > 0 {
		this.Ctx.WriteString(fmt.Sprintf("path is: %s\n", path))
	}
	ext := this.Ctx.Input.Param(":ext")
	if len(ext) > 0 {
		this.Ctx.WriteString(fmt.Sprintf("ext is: %s\n", ext))
	}
}

func main() {
	// RESTful Controller 路由
	beego.Router("/user", &UserController{})
	// 正则路由
	beego.Router("/users/?:id", &RegExpController{})
	beego.Router("/re/num/:id([0-9]+)", &RegExpController{})
	beego.Router("/re/num/user:id([0-9]+)de", &RegExpController{})
	beego.Router("/re/alpha/:id([\\w]+)", &RegExpController{})
	beego.Router("/re/wild/*", &RegExpController{})
	beego.Router("/re/path/*.*", &RegExpController{})

	beego.Run(":18081")
}
