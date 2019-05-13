.PHONY: clean all

GCC			:= gcc -O2 -Wall
BIN_DIR	:= ../../../bin

PKG_LIBUV = $(shell pkg-config --cflags --libs libuv)
TINY_WEB_1 := $(BIN_DIR)/tinyweb1
TINY_WEB_2 := $(BIN_DIR)/tinyweb2
TINY_WEB_3 := $(BIN_DIR)/tinyweb3


# ####################################
# Dashboard AREA
# ####################################
all: init env $(TINY_WEB_1) $(TINY_WEB_2) $(TINY_WEB_3)

$(TINY_WEB_1): main1.c ../membuf.c ../tinyweb1.c
	$(GCC) -o $@ $^ $(PKG_LIBUV)
$(TINY_WEB_2): main2.c ../membuf.c ../tinyweb2.c
	$(GCC) -o $@ $^ $(PKG_LIBUV)
$(TINY_WEB_3): main3.c ../membuf.c ../tinyweb3.c
	$(GCC) -o $@ $^ $(PKG_LIBUV)

env:
	echo PKG_CONFIG_PATH=$(PKG_CONFIG_PATH)
init:
	[ ! -d $(BIN_DIR) ] && mkdir -p $(BIN_DIR) || >/dev/null


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.log *.bak
	rm -rvf *.dSYM
