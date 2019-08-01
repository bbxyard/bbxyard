# netdata 管理

DK_RUN  := docker run --rm --privileged -v/etc/localtime:/etc/localtime -v/home:/home

TAG     := netdata-c7:0.1
NAME    := netdata


# ####################################
# Dashboard AREA
# ####################################
start:
	$(DK_RUN) -d -h $(NAME) --name $(NAME) -p19999:19999 $(TAG)
	docker ps -a
stop:
	docker stop $(NAME)
bash:
	docker exec -it $(NAME) /bin/bash
status:
	docker ps -a


# ####################################
# Init AREA
# ####################################
build:
	docker build -t $(TAG) https://raw.githubusercontent.com/0a0a/docker/master/df/dashboard/netdata-centos.dockerfile
	docker images


# ####################################
# Fini AREA
# ####################################
clean:
	-docker rmi $(TAG)
	-rm -rvf *.bak *.log
