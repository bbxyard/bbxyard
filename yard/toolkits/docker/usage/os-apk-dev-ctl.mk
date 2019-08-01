# OS-Alpine-dev 管理

# 固定参数部分
DK_RUN  := docker run --rm --privileged -v/etc/localtime:/etc/localtime -v/home:/home

# 变化参数部分
TAG       := os-apk-dev:0.1
NAME      := os-apk-dev
DF_URL    := https://raw.githubusercontent.com/0a0a/docker/master/df/1st/tiny-os-dev.dockerfile
START_CMD := /bin/bash
DK_V_OPT  := 


# ####################################
# Dashboard AREA
# ####################################
start: do-start do-show-ps
stop: do-stop do-show-ps do-show-images
status: do-show-ps
bash:
	docker exec -it $(NAME) /bin/bash

do-start:
	$(DK_RUN) -d -h $(NAME) --name $(NAME) $(DK_V_OPT) $(TAG) $(START_CMD)
do-stop:
	docker stop $(NAME)
do-show-ps:
	@echo
	docker ps -a
	@echo
do-show-images:
	@echo
	docker images
	@echo


# ####################################
# Init AREA
# ####################################
build: do-build do-show-images
do-build:
	docker build -t $(TAG) $(DF_URL)


# ####################################
# Fini AREA
# ####################################
clean:
	-docker rmi $(TAG)
	-rm -rvf *.bak *.log
