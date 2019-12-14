# ####################################
# Name: microservice-demo
# FileVersion: 20191214
# ####################################


DK	:= docker
DC	:= docker-compose
DK_EXEC := docker exec -it -u foobar
SELF_DO := make --no-print-directory -C .

DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")

-include ./.env

PROJ_NAME := $(shell basename $(CURDIR))

CMD := bash


# ####################################
# Configure AREA
# ####################################


# ####################################
# Dashboard AREA
# ####################################


# ####################################
# Gen AREA
# ####################################
gen: gen-pb
gen-pb:
	# protoc -Istub/protos --micro_out=src/pbs/ --go_out=src/pbs/ stub/protos/greeter.proto
	protoc -Istub/protos --micro_out=src/pbs/ --go_out=plugins=grpc:src/pbs/ greeter.proto


# ####################################
# Deps AREA
# ####################################
deps:
	make -C src $@


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf bin/* src/pbs/*
