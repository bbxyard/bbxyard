# ####################################
# 数据库脚本 AREA
# ####################################
STAR_DB_NAME	:= go_iris_superstar
STAR_DB_CMD_CREATE := CREATE DATABASE IF NOT EXISTS ${STAR_DB_NAME} DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
STAR_INIT_SQL_FILE := $(CURDIR)/go-iris-superstar/stub/db-superstar-baseline.sql

init-star-db:
	$(SQL_MK) sh CMD='$(MYSQL) -se"${STAR_DB_CMD_CREATE}"'
	$(SQL_MK) exec CMD='$(MYSQL_IMPORT) -D${STAR_DB_NAME} < $(STAR_INIT_SQL_FILE)'

export-star-db: backup_dir
	$(SQL_MK) exec CMD='${MYSQL_EXPORT} ${STAR_DB_NAME} > ${BACKUP_DIR}/db-${STAR_DB_NAME}-${DATA_SUF}.sql'
