# ####################################
# @file: Makefile
# @name: yard/skills/07-mysql
# @date: 2020-01-01 00:51:47
# @tver: 20191217
# ####################################


DK      := docker
DC      := docker-compose
DK_EXEC := docker exec -it

DATA_SUF:= $(shell date +"%Y.%m.%d.%H.%M.%S")

DB_NAME	:= smoke
DB_USER := root
DB_PASSWD := passwd

DB_CMD_CREATE := CREATE DATABASE IF NOT EXISTS ${DB_NAME} DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


# ####################################
# Dashboard AREA
# ####################################
demo:
	node node/hallo-mysql.js | tee -ai run.log


# ####################################
# Docker AREA
# ####################################
start-mysql:
	make -C .open-helper/mysql up
stop-mysql:
	make -C .open-helper/mysql down

init:
	make -C .open-helper/mysql sh CMD='mysql -u${DB_USER} -p${DB_PASSWD} -se"${DB_CMD_CREATE}"'
	make -C .open-helper/mysql sh CMD='mysql -u${DB_USER} -p${DB_PASSWD} -se"show databases;"'
	make -C .open-helper/mysql exec CMD='mysql -u${DB_USER} -p${DB_PASSWD} -D${DB_NAME} < ./stub/init.sql'
sh:
	make -C .open-helper/mysql sh


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.log
