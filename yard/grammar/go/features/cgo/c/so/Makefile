
SO_FILE := libyh_cgo_test.so
LIB_FILE := yh_cgo_test.a
CUR_SO_DIR := ./lib

CGO_WITH_SRC_DIR := ../../cgo-with-src/c

CGO_WITH_SO_INC_DIR := ../../cgo-with-so/include
CGO_WITH_SO_LIB_DIR := ../../cgo-with-so/lib
CGO_WITH_SO_FILE := $(CGO_WITH_SO_LIB_DIR)/$(SO_FILE)

$(CUR_SO_DIR):
	mkdir -p $@
$(CGO_WITH_SO_INC_DIR):
	mkdir -p $@
$(CGO_WITH_SO_LIB_DIR):
	mkdir -p $@

$(CGO_WITH_SRC_DIR):
	mkdir -p $@

cgo-with-so: $(CUR_SO_DIR) $(CGO_WITH_SO_LIB_DIR) $(CGO_WITH_SO_INC_DIR)
	gcc test.c -shared -o $(CUR_SO_DIR)/$(SO_FILE)
	cp $(CUR_SO_DIR)/$(SO_FILE) $(CGO_WITH_SO_FILE)
	cp test.h $(CGO_WITH_SO_INC_DIR)/

cgo-with-src: $(CGO_WITH_SRC_DIR)
	cp test.h test.c $</
