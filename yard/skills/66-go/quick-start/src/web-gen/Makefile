DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ORG_GOPATH = ${HOME}/opt/yh-go
PROJ_PATH = $(shell pwd)
APP_NAME = beego-demo


SLN_ROOT_PATH := $(shell cd ../.. && pwd)


# ####################################
# Dashboard AREA
# ####################################
init:
	go mod init $(APP_NAME)

status:
	echo ${SLN_ROOT_PATH}


# ####################################
# Kits AREA
# ####################################
gen: export GO111MODULE=off
gen: export GOPATH=$(SLN_ROOT_PATH)
gen: beego-api-demo beego-new-demo
	# bee generate scaffold user -fields="id:int64,name:string,gender:int,age:int" -driver=mysql -conn="hero@tcp(127.0.0.1:3307)/imooc_beego"
beego-api-demo:
	bee api beego-api-demo
beego-new-demo:
	bee new beego-new-demo


run: export GO111MODULE=off
run: export GOPATH=$(PROJ_PATH)
run:
	echo $(GOPATH)
	bee run


deps:
	go get -u github.com/astaxie/beego
