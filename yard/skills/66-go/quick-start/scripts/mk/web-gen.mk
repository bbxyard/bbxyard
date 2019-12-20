# ####################################
# Web-gen 样例
# ####################################


WEB_GEN_BEEGO_API_DEMO_PORT := 18801
WEB_GEN_BEEGO_NEW_DEMO_PORT := 18802


# ####################################
# Dashboard AREA
# ####################################
# run-web-mvc: run-web-mvc-beego-normal-case \
# 	run-web-mvc-gin-normal-case \
# 	run-web-mvc-iris-normal-case

start-web-gen-beego-api:
	cd $(SRC_DIR)/web-gen/beego-api-demo && go run main.go

start-web-gen-beego-new:
	cd $(SRC_DIR)/web-gen/beego-new-demo && go run main.go
