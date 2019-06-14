.PHONY: clean all

OUT_DIR = out

# ####################################
# Dashboard AREA
# ####################################
start:
	scrapy crawl meiju


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
