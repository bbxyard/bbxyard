# ####################################
# Name: mysql 
# FileVersion: 20191230
# ####################################


DK	:= docker
DC	:= docker-compose
DK_EXEC := docker exec -it

DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")

PROJ_NAME := $(shell basename $(CURDIR))-demo-server

CMD := bash

-include ./.env
-include ../../yh-prod.env


# ####################################
# Dashboard AREA
# ####################################
start: up
stop: down
up: init-dir
	$(DC) up -d
down:
	$(DC) down --remove-orphans 
config:
	$(DC) config
start-fg: init-dir
	$(DC) up

sh:
	$(DK_EXEC) $(PROJ_NAME) $(CMD)
bash: sh

exec:
	docker exec -i $(PROJ_NAME) $(CMD)


# ####################################
# Init AREA
# 	init-dir: 
# ####################################
init-dir:
	# 创建数据目录(-dLf), 也可以直接-e来判存
	$(DC) config | grep ":rw$$" | grep -o " /[^:]*" | grep "/[^.]*$$" | grep -o "[^ ]*" | while read x; do \
		echo "verify dir/link $$x"; \
		[ -d "$$x" -o -L "$$x" -o -f "$$x" ] || mkdir -p "$$x"; \
		echo $$x; \
	done;


# ####################################
# Utils AREA
# ####################################
purge:
	make -C . down
	[ ! -d data ] || sudo rm -rvf data
	[ ! -d log ] || sudo rm -rvf log

clean:
	rm -rvf log/* *.bak
