# ####################################
# Web-gen 样例
# ####################################


WEB_GEN_BEEGO_API_DEMO_PORT := 18801
WEB_GEN_BEEGO_NEW_DEMO_PORT := 18802


# ####################################
# Dashboard AREA
# ####################################
run-web-gen: run-web-gen-beego-api-normal-case \
	run-web-gen-beego-new-normal-case

start-web-gen-beego-api:
	cd $(SRC_DIR)/web-gen/beego-api-demo && go run main.go

start-web-gen-beego-new:
	cd $(SRC_DIR)/web-gen/beego-new-demo && go run main.go


# ####################################
# Beego-api AREA
# ####################################
run-web-gen-beego-api-normal-case:
	curl localhost:${WEB_GEN_BEEGO_API_DEMO_PORT}

# ####################################
# Beego-new AREA
# ####################################
run-web-gen-beego-new-normal-case:
	curl localhost:${WEB_GEN_BEEGO_NEW_DEMO_PORT}
