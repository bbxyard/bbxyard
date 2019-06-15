.PHONY: clean all

OUT_DIR = out
OUT_FILE = output.jl
LOG_FILE = output.log

# ####################################
# Dashboard AREA
# ####################################
start: $(OUT_DIR)
	scrapy crawl stackoverflow --logfile=$(OUT_DIR)/$(LOG_FILE) --output=$(OUT_DIR)/$(OUT_FILE) --pidfile=$(OUT_DIR)/run.pid


# ####################################
# Deps AREA
# ####################################
$(OUT_DIR):
	mkdir -p $@


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.log *.bak
	[ -d out ] && rm -rvf out/* || >/dev/null
