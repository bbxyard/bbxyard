# ####################################
# @file: Makefile
# @name: yard/skills/99-imooc/go-iris-superstar
# @date: 2020-01-05 17:04:49
# @tver: 20191217
# ####################################


DK      := docker
DC      := docker-compose
DK_EXEC := docker exec -it

DATA_SUF:= $(shell date +"%Y.%m.%d.%H.%M.%S")


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
# supervisor 托管区域
# ####################################
start-xx1-fg:
start-xx2-fg:


# ####################################
# Init AREA
# ####################################
go-deps: go-deps-cur
go-deps-once:
	go env -w GOPROXY=https://goproxy.cn,direct
	go env -w GO111MODULE=on
	go get -u golang.org/x/tools/cmd/goimport
go-deps-cur:
	go get -u github.com/kataras/iris
	go get -u github.com/gorilla/securecookie
	go get -u github.com/go-xorm/xorm
	go get -u github.com/go-sql-driver/mysql


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
	[ -d /tmp/99-go-imooc ] || mkdir -p /tmp/99-go-imooc


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
	-$(DK) ps -a | grep Exited | awk '{print $$1}' | xargs $(DK) rm
	-$(DK) images | grep "^<none>" | awk '{print $$3}' | xargs $(DK) rmi
