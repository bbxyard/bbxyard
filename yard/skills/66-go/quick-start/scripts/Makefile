# ####################################
# @file: Makefile
# @name: /home/devhub/xgit/github.com/bbxyard/bbxyard/yard/skills/66-go/quick-start/src/restful
# @date: 2019-12-19 16:29:41
# @tver: 20191217
# ####################################


DK      := docker
DC      := docker-compose
DK_EXEC := docker exec -it

DATA_SUF:= $(shell date +"%Y.%m.%d.%H.%M.%S")

SRC_DIR	:= ../src


# ####################################
# Dashboard AREA
# ####################################
up: start-sv
down: stop-sv
status: status-sv
ctl: ctl-sv
demo: start-client

start-client:
	echo it works.


# ####################################
# Test AREA
# ####################################
include ./mk/test-restful.mk
include ./mk/test-web.mk


# ####################################
# supervisor 托管区域
# ####################################
start-restful-beego:
	go run $(SRC_DIR)/restful/beego/main.go
start-restful-gin:
	go run $(SRC_DIR)/restful/gin/main.go
start-restful-gin:
	go run $(SRC_DIR)/restful/gin/main.go

start-web-mini-iris:
	cd $(SRC_DIR)/web-mini/iris && go run main.go


# ####################################
# supervisor AREA
# ####################################
start-sv: init-sv
	supervisord -c ./supervisord.conf
stop-sv:
	supervisorctl -c ./supervisord.conf stop all
	supervisorctl -c ./supervisord.conf shutdown
status-sv:
	supervisorctl -c ./supervisord.conf status
ctl-sv:
	supervisorctl -c ./supervisord.conf
init-sv:
	[ -d /tmp/quick-start ] || mkdir -p /tmp/quick-start


# ####################################
# Deps AREA
# ####################################
go-deps:
	go get -u github.com/gin-gonic/gin


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
	-$(DK) ps -a | grep Exited | awk '{print $$1}' | xargs $(DK) rm
	-$(DK) images | grep "^<none>" | awk '{print $$3}' | xargs $(DK) rmi
