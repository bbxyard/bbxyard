# ####################################
# Web 样例
# ####################################


BEEGO_WEB_PORT := 18091
GIN_WEB_PORT   := 18092
IRIS_WEB_PORT  := 18094



# ####################################
# Dashboard AREA
# ####################################
run-web: run-web-beego-normal-case run-web-gin-normal-case run-web-iris-normal-case


# ####################################
# Beego AREA
# ####################################
run-web-beego-normal-case:


# ####################################
# Gin AREA
# ####################################
run-web-gin-normal-case:


# ####################################
# Iris AREA
# ####################################
run-web-iris-normal-case:
	curl "http://localhost:${IRIS_WEB_PORT}"
	@echo; echo
	curl "http://localhost:${IRIS_WEB_PORT}/json"
	@echo; echo
	curl "http://localhost:${IRIS_WEB_PORT}/hallo"


# ####################################
# Misc AREA
# ####################################
run-web-favicon:
	md5sum ../src/web/static/favicons/favicon.ico
	curl -s localhost:${IRIS_WEB_PORT}/favicon.ico | md5sum
