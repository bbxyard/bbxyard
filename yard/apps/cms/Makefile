NPM = cnpm

STUB_ROOT = ../../../stub
NAPP_STUB_DIR = ${STUB_ROOT}/node

GHOST_VER = 2.14.3
GHOST_DOWN_URL = https://github.com/TryGhost/Ghost/releases/download/${GHOST_VER}/Ghost-${GHOST_VER}.zip
GHOST_ZIP = ${NAPP_STUB_DIR}/Ghost-${GHOST_VER}.zip

# #################################
# Main
# #################################
.PHONY: all clean
all: ghost-${GHOST_VER}
clean:
	-rm -rvf *.log *.bak

# #################################
# Each
# #################################
ghost-${GHOST_VER}:
	mkdir -p "$@" && unzip ${NAPP_STUB_DIR}/Ghost-${GHOST_VER}.zip -d "$@"


# ####################
# depressed
# ###################
ghost-cli:
	which ghost >/dev/null || ${NPM} install -g ghost-cli
	# && ghost install local

# ####################
# deps
# ###################
${GHOST_ZIP}:
	cd ${NAPP_STUB_DIR} && wget ${GHOST_DOWN_URL}
