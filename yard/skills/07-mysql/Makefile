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
DB_CMD_IMPORT_OPT := --default-character-set=utf8
DB_CMD_DUMP_OPT := --skip-dump-date --skip-extended-insert --default-character-set=utf8
BACKUP_DIR := .backup


# ####################################
# Dashboard AREA
# ####################################
up: start-mysql
down: stop-mysql
test: init do-test


# ####################################
# Docker AREA
# ####################################
start-mysql:
	make -C .open-helper/mysql up
stop-mysql:
	make -C .open-helper/mysql down

sh:
	make -C .open-helper/mysql sh

init: init-db init-deps
init-db:
	make -C .open-helper/mysql sh CMD='mysql -u${DB_USER} -p${DB_PASSWD} -se"${DB_CMD_CREATE}"'
	make -C .open-helper/mysql sh CMD='mysql -u${DB_USER} -p${DB_PASSWD} -se"show databases;"'
	make -C .open-helper/mysql exec CMD='mysql -u${DB_USER} -p${DB_PASSWD} ${DB_CMD_IMPORT_OPT} -D${DB_NAME} < ./stub/init.sql'
init-deps:
	cd node && cnpm i

do-test:
	cd node && node hallo-mysql.js

export:
	[ -d "${BACKUP_DIR}" ] || mkdir -p "${BACKUP_DIR}"
	make -C .open-helper/mysql exec CMD='mysqldump -u${DB_USER} -p${DB_PASSWD} ${DB_CMD_DUMP_OPT} ${DB_NAME} > ../../${BACKUP_DIR}/db-${DB_NAME}-${DATA_SUF}.sql'


# ####################################
# Utils AREA
# ####################################
purge: stop-mysql
	make -C .open-helper/mysql $@
	rm -rvf node/node_modules
	du -sh .

clean:
	rm -rvf *.log
