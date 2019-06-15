.PHONY: clean all

ARCHIVE_DIR := ~/backup
NOW_LABEL 	:= $(shell date '+%Y%m%d')

# ####################################
# Dashboard AREA
# ####################################
tar: tar-so tar-movie tar-pic tar-misc


# ####################################
# TAR AREA
# ####################################
tar-so:
	$(call archDir,spider-so)
tar-movie:
	$(call archDir,spider-movie-meijutt)
tar-pic:
	$(call archDir,spider-pic-xiaohuar)
tar-misc:
	$(call archDir,spider-misc)


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log

# 归档目录
define archDir
	tar -jcvf $(ARCHIVE_DIR)/$(1)-$(NOW_LABEL).tar.bz2 $(1)
endef
