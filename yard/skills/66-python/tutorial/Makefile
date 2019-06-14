.PHONY: clean all

OUT_DIR = out
OUT_FMT = json xml csv jsonlines jl marshal pickle
OUT_FILES = $(foreach ext, $(OUT_FMT), output.$(ext))

# ####################################
# Dashboard AREA
# ####################################
start: $(OUT_DIR) crawl-site

# 多种输出格式Demo
crawl-all-fmt:
	for outfile in $(OUT_FILES); \
	do \
		scrapy crawl quotes -o $(OUT_DIR)/$$outfile; \
	done

# 爬取整站
crawl-site:
	scrapy crawl quotes-all -o $(OUT_DIR)/output-site.jl


# ####################################
# Deps AREA
# ####################################
# 输出目录
$(OUT_DIR):
	mkdir -p "$@"


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
	rm quotes-*.html
	rm out/output.*
