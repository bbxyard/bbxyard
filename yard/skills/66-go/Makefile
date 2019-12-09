
# ####################################
# Config AREA
# ####################################
COBRA_DEMO := cobra-demo
COBRA_DEMO_PKG := bbxyard.com/cli/cobra-demo
AUTHOR := boxu@yvhai.com


# ####################################
# Dashboard AREA
# ####################################
init: init-cobra-demo


# ####################################
# Deps AREA
# ####################################
deps: deps-http deps-logs

deps-http:
	go get -u github.com/gin-gonic/gin

deps-logs:
	go get -u go.uber.org/zap


# ####################################
# Init AREA
# ####################################
init-cobra-demo:
	cobra init $(COBRA_DEMO) --pkg-name=$(COBRA_DEMO_PKG)
	cd $(COBRA_DEMO) && go mod init $(COBRA_DEMO_PKG)
init-cobra-demo-ext:
	cd $(COBRA_DEMO) \
		&& cobra add version -a $(AUTHOR) \
		&& cobra add push -a $(AUTHOR) \
		&& cobra add pull -a $(AUTHOR) \
		&& pwd
