package routes

import (
	"go-iris-superstar/bootstrap"
	"go-iris-superstar/services"
	"go-iris-superstar/web/controllers"
	"go-iris-superstar/web/middleware"

	"github.com/kataras/iris/mvc"
)

// Configure registers the necessary routes to the app.
func Configure(b *bootstrap.Bootstrapper) {
	superstarService := services.NewSuperstarService()

	index := mvc.New(b.Party("/"))
	index.Register(superstarService)
	index.Handle(new(controllers.IndexController))

	admin := mvc.New(b.Party("/admin"))
	admin.Router.Use(middleware.BasicAuth)
	admin.Register(superstarService)
	admin.Handle(new(controllers.AdminController))

	//b.Get("/follower/{id:long}", GetFollowerHandler)
	//b.Get("/following/{id:long}", GetFollowingHandler)
	//b.Get("/like/{id:long}", GetLikeHandler)
}
