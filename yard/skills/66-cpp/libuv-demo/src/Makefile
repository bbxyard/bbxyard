.PHONY: clean

PKG_CONFIG_PATH = /usr/local/lib/pkgconfig

# ####################################
# Dashboard AREA
# ####################################
all:
	# PKG_CONFIG_PATH=$(PKG_CONFIG_PATH) make -C misc env
	make -C misc
	make -C tinyweb

# ####################################
# Utils AREA
# ####################################
clean:
	make -C misc clean
	make -C tinyweb clean
