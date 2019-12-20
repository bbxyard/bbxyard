# ####################################
# @file: Makefile
# @name: quick-start/src
# @date: 2019-12-20 11:58:35
# @tver: 20191217
# ####################################


DATA_SUF:= $(shell date +"%Y.%m.%d.%H.%M.%S")


# ####################################
# Deps AREA
# ####################################
go-deps: go-deps-beego go-deps-gin go-deps-iris


go-deps-beego:
	go get -u github.com/astaxie/beego

go-deps-gin:
	go get -u github.com/gin-gonic/gin

go-deps-iris:
	go get -u github.com/kataras/iris


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log

