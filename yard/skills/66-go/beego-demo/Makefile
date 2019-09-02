DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ORG_GOPATH = ${HOME}/opt/yh-go
PROJ_PATH = $(shell pwd)
APP_NAME = beego-demo

ifdef MSG
	GUP_MSG = "$(MSG)"
endif


# ####################################
# Dashboard AREA
# ####################################
init:
	go mod init $(APP_NAME)


# ####################################
# Kits AREA
# ####################################
gen: export GO111MODULE=off
gen: export GOPATH=$(PROJ_PATH)
gen:
	bee generate scaffold user -fields="id:int64,name:string,gender:int,age:int" -driver=mysql -conn="hero@tcp(127.0.0.1:3307)/imooc_beego"

run: export GO111MODULE=off
run: export GOPATH=$(PROJ_PATH)
run:
	echo $(GOPATH)
	bee run


# ####################################
# git
# ####################################
gpom:
	git add .
	git commit -am $(GUP_MSG)
	git push origin master
	git status
gfom:
	git pull origin master
gs:
	git status
ga:
	git add .
