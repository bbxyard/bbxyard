/**
 * 首页目录的Controller
 * http://localhost:8080/
 */
package controllers

import (
	"go-iris-superstar/datasource"
	"go-iris-superstar/models"
	"go-iris-superstar/services"
	"log"

	"github.com/kataras/iris"
	"github.com/kataras/iris/mvc"
)

type IndexController struct {
	Ctx     iris.Context
	Service services.SuperstarService
}

// http://localhost:8080/
func (c *IndexController) Get() mvc.Result {
	datalist := c.Service.GetAll()
	return mvc.View{
		Name: "index.html",
		Data: iris.Map{
			"Title":    "球星库",
			"Datalist": datalist,
		},
	}
}

// http://localhost:8080/{id}
func (c *IndexController) GetBy(id int) mvc.Result {
	if id < 1 {
		return mvc.Response{
			Path: "/",
		}
	}

	data := c.Service.Get(id)
	return mvc.View{
		Name: "info.html",
		Data: iris.Map{
			"Title": "球星库",
			"Info":  data,
		},
	}
}

// http://localhost:8080/search?country=巴西
func (c *IndexController) GetSearch() mvc.Result {
	country := c.Ctx.URLParam("country")
	datalist := c.Service.Search(country)
	return mvc.View{
		Name: "index.html",
		Data: iris.Map{
			"Title":    "球星库",
			"Datalist": datalist,
		},
	}
}

// 集群多服务器的时候，才用得上这个接口
// 性能优化的时候才考虑，加上本机的SQL缓存
// http://localhost:8080/clearcache
func (c *IndexController) GetClearcache() mvc.Result {
	err := datasource.InstanceMaster().ClearCache(&models.StarInfo{})
	if err != nil {
		log.Fatal(err)
	}
	return mvc.Response{
		Text: "xorm缓存清除成功",
	}
}
