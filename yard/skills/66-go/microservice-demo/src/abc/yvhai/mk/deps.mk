# ####################################
# Deps AREA
# ####################################


# ####################################
# Micro Service AREA
# ####################################
deps: deps-http deps-logs deps-grpc deps-msvc

deps-http:
	# go get -u github.com/gin-gonic/gin

deps-logs:
	# go get -u go.uber.org/zap

deps-grpc:
	go get -u google.golang.org/grpc
	go get -u github.com/golang/protobuf/protoc-gen-go
	go get -u github.com/micro/protoc-gen-micro

deps-msvc:
	go get -u github.com/go-kit/kit
	go get -u github.com/micro/go-micro
	go get -u github.com/NYTimes/gizmo
	go get -u github.com/NYTimes/gziphandler
	go get -u github.com/sirupsen/logrus
#	go get -u github.com/hashicorp/consul
	go get -u github.com/gorilla/mux

deps-msvc-deps:
	go get -u github.com/oklog/oklog/pkg/group
	go get -u github.com/prometheus/client_golang


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
