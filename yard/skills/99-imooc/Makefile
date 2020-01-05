# ####################################
# @file: Makefile
# @name: yard/skills/99-imooc
# @date: 2020-01-01 00:51:47
# @tver: 20191217
# ####################################


DK      := docker
DC      := docker-compose
DK_EXEC := docker exec -it

DATA_SUF:= $(shell date +"%Y.%m.%d.%H.%M.%S")

DB_USER := root
DB_PASSWD := passwd

DB_CMD_IMPORT_OPT := --default-character-set=utf8
DB_CMD_DUMP_OPT := --skip-dump-date --skip-extended-insert --default-character-set=utf8
BACKUP_DIR := $(CURDIR)/.backup

MYSQL 	:= mysql -u${DB_USER} -p${DB_PASSWD}
MYSQL_IMPORT := mysql -u${DB_USER} -p${DB_PASSWD} ${DB_CMD_IMPORT_OPT}
MYSQL_EXPORT := mysqldump -u${DB_USER} -p${DB_PASSWD} ${DB_CMD_DUMP_OPT}

SQL_MK 	:= make -C .open-helper/mysql


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
	$(SQL_MK) up
stop-mysql:
	$(SQL_MK) down
sh:
	$(SQL_MK) sh

init: init-db init-deps
init-db:
	$(SQL_MK) sh CMD='$(MYSQL) -se"show databases;"'
init-deps:
	cd node && cnpm i

do-test:

backup_dir:
	[ -d "${BACKUP_DIR}" ] || mkdir -p "${BACKUP_DIR}"


# ####################################
# Sub AREA
# ####################################
include go-iris-superstar/stub/db.mk


# ####################################
# Utils AREA
# ####################################
purge: stop-mysql
	$(SQL_MK) $@
	du -sh .

clean:
	rm -rvf *.log
