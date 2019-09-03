.PHONY: clean all

GCC 		:= gcc -O2 -g -Wall
BIN_DIR := ../../bin


# ####################################
# Dashboard AREA
# ####################################
all: init env $(BIN_DIR)/thread_demo $(BIN_DIR)/thread_sum $(BIN_DIR)/tcp_server
$(BIN_DIR)/thread_demo: thread_demo.c
	$(GCC) -o $@ $^ `pkg-config --cflags --libs libuv`	
$(BIN_DIR)/thread_sum: thread_sum.cc
	$(GCC) -o $@ $< $(shell pkg-config --cflags --libs libuv)
$(BIN_DIR)/tcp_server: tcp_server.c
	$(GCC) -o $@ $< $(shell pkg-config --cflags --libs libuv)

env:
	echo PKG_CONFIG_PATH=$(PKG_CONFIG_PATH)
init:
	[ ! -d $(BIN_DIR) ] && mkdir -p $(BIN_DIR) || >/dev/null


# ####################################
# Utils AREA
# ####################################
demo:
	export PKG_CONFIG_PATH=/usr/local/lib/pkgconfig:$(PKG_CONFIG_PATH) \
		&& echo $PKG_CONFIG_PATH \
		&& pkg-config --cflags --libs libuv \
		&& PKG_CONFIG_PATH=/usr/local/lib/pkgconfig:$(PKG_CONFIG_PATH)  gcc thread_demo.c -Wformat $(shell pkg-config --cflags --libs libuv)
clean:
	rm -rvf *.log *.bak
	rm -rvf *.dSYM
