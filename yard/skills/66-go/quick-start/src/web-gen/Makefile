DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

PROJ_PATH = $(shell pwd)
MOD_NAME  = web-gen

SLN_ROOT_PATH := $(shell cd ../.. && pwd)
DIST_DIR      := $(SLN_ROOT_PATH)/dist


# ####################################
# Dashboard AREA
# ####################################
init:
	go mod init $(MOD_NAME)

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


# ####################################
# Pack AREA
# ####################################
pack: pack-beego
pack-beego: export GOPATH=$(SLN_ROOT_PATH)
pack-beego: dist
	@echo GOPATH=${GOPATH}
	cd beego-api-demo && bee pack -o "${DIST_DIR}"
	cd beego-new-demo && bee pack -o "${DIST_DIR}"


# ####################################
# Distribute AREA
# ####################################
dist:
	[ -d "${DIST_DIR}" ] || mkdir -p "${DIST_DIR}"


# ####################################
# Deps AREA
# ####################################
deps:
	go get -u github.com/astaxie/beego
