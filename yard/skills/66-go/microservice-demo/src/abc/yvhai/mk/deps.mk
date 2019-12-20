# ####################################
# Deps AREA
# ####################################


# ####################################
# Init AREA
# ####################################
go-init:
	go env -w GOPROXY=https://goproxy.cn,direct
	go env -w GO111MODULE=on
	go get -u golang.org/x/tools/cmd/goimports


# ####################################
# Micro Service AREA
# ####################################
deps: deps-http deps-logs deps-grpc deps-msvc

deps-http:
	go get -u github.com/gin-gonic/gin

deps-logs:
	go get -u go.uber.org/zap

deps-grpc:
	go get -u google.golang.org/grpc
	go get -u github.com/golang/protobuf/protoc-gen-go
	go get -u github.com/micro/protoc-gen-micro

deps-msvc: deps-msvc-micro deps-msvc-gizmo deps-msvc-go-kit deps-msvc-deps
deps-msvc-micro:
	go get -u github.com/micro/micro
	go get -u github.com/micro/go-micro
deps-msvc-gizmo:
	go get -u github.com/NYTimes/gizmo
	go get -u github.com/NYTimes/gziphandler
deps-msvc-go-kit:
	go get -u github.com/go-kit/kit
deps-msvc-deps:
	go get -u github.com/oklog/oklog/pkg/group
	go get -u github.com/prometheus/client_golang
	go get -u github.com/sirupsen/logrus
	go get -u github.com/gorilla/mux


# ####################################
# Web AREA
# ####################################
deps-web: deps-web-beego deps-web-gin deps-web-echo deps-web-iris
deps-web-beego:
	go get -u github.com/astaxie/beego
	go get -u github.com/beego/bee
deps-web-gin:
	go get -u github.com/gin-gonic/gin
deps-web-echo:
deps-web-iris:
	go get -u github.com/kataras/iris


# ####################################
# WX AREA
# ####################################
deps-wx:
	go get -u github.com/silenceper/wechat


# ####################################
# Res AREA
# ####################################
deps-res-area:
	# 中国行政区划代码
	go get -u github.com/ppmoon/gbt2260


# ####################################
# DB AREA
# ####################################
deps-db:
	go get -u go.mongodb.org/mongo-driver/mongo
