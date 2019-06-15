.PHONY: clean all

OUT_DIR = out

OUT_ZHIHU_PATH = $(OUT_DIR)/zhihu
OUT_CSDN_PATH = $(OUT_DIR)/csdn

# ####################################
# Dashboard AREA
# ####################################
init: $(OUT_ZHIHU_PATH) $(OUT_CSDN_PATH)


# ####################################
# OutputDir AREA
# ####################################
$(OUT_ZHIHU_PATH):
	mkdir -p "$@"
$(OUT_CSDN_PATH):
	mkdir -p "$@"


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.log *.bak
	[ -d "$(OUT_ZHIHU_PATH)" ] && rm -rvf $(OUT_ZHIHU_PATH)/* || >/dev/null
	[ -d "$(OUT_CSDN_PATH)" ] && rm -rvf $(OUT_CSDN_PATH)/* || >/dev/null
