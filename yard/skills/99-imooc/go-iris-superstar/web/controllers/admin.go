package controllers

import (
	"go-iris-superstar/models"
	"go-iris-superstar/services"

	"github.com/kataras/iris"
	"github.com/kataras/iris/mvc"
)

type AdminController struct {
	Ctx     iris.Context
	Service services.SuperstarService
}

func (c *AdminController) Get() mvc.Result {
	datalist := c.Service.GetAll()
	// set the models and render the  view template.
	return mvc.View{
		Name: "admin/index.html",
		Data: iris.Map{
			"Title":    "管理后台",
			"Datalist": datalist,
		},
		Layout: "admin/layout.html", // 不要和前端的layout混用
	}
}

func (c *AdminController) GetEdit() mvc.Result {
	id, err := c.Ctx.URLParamInt("id")
	var data *models.StarInfo
	if err != nil {
		data = &models.StarInfo{Id: 0}
	} else {
		data = c.Service.Get(id)
	}
	// set the model and render the view template.
	return mvc.View{
		Name: "admin/edit.html",
		Data: iris.Map{
			"Title": "管理后台",
			"Info":  data,
		},
		Layout: "admin/layout.html", // 不要和前端的layout混用
	}
}
