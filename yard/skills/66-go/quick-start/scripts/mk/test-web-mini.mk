# ####################################
# Web 样例
# ####################################


BEEGO_WEB_MINI_PORT := 18091
GIN_WEB_MINI_PORT   := 18092
IRIS_WEB_MINI_PORT  := 18094



# ####################################
# Dashboard AREA
# ####################################
run-web-mini: run-web-mini-beego-normal-case run-web-mini-gin-normal-case run-web-mini-iris-normal-case


# ####################################
# Beego AREA
# ####################################
run-web-mini-beego-normal-case:


# ####################################
# Gin AREA
# ####################################
run-web-mini-gin-normal-case:


# ####################################
# Iris AREA
# ####################################
run-web-mini-iris-normal-case:
	curl "http://localhost:${IRIS_WEB_MINI_PORT}"
	@echo; echo
	curl "http://localhost:${IRIS_WEB_MINI_PORT}/json"
	@echo; echo
	curl "http://localhost:${IRIS_WEB_MINI_PORT}/hallo"


# ####################################
# Misc AREA
# ####################################
run-web-mini-favicon:
	md5sum ../src/web/static/favicons/favicon.ico
	curl -s localhost:${IRIS_WEB_MINI_PORT}/favicon.ico | md5sum
