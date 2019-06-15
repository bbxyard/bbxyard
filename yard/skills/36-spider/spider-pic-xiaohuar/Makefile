.PHONY: clean all

OUT_DIR = out
OUT_FILE = output.jl
LOG_FILE = xh.log
DOWN_DIR = xiaohuar.com

# ####################################
# Dashboard AREA
# ####################################
start: $(OUT_DIR) $(DOWN_DIR)
	scrapy crawl xh --logfile=$(OUT_DIR)/$(LOG_FILE) --output=$(OUT_DIR)/$(OUT_FILE) --pidfile=$(OUT_DIR)/run.pid


# ####################################
# Deps AREA
# ####################################
$(OUT_DIR):
	mkdir -p $@
$(DOWN_DIR):
	mkdir -p $@


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.log *.bak
	[ -d out ] && rm -rvf out/* || >/dev/null
