# ####################################
# Web 样例
# ####################################


BEEGO_WEB_MVC_PORT := 18191
GIN_WEB_MVC_PORT   := 18192
IRIS_WEB_MVC_PORT  := 18194



# ####################################
# Dashboard AREA
# ####################################
run-web-mvc: run-web-mvc-beego-normal-case run-web-mvc-gin-normal-case run-web-mvc-iris-normal-case


# ####################################
# Beego AREA
# ####################################
run-web-mvc-beego-normal-case:


# ####################################
# Gin AREA
# ####################################
run-web-mvc-gin-normal-case:


# ####################################
# Iris AREA
# ####################################
run-web-mvc-iris-normal-case:
	curl "http://localhost:${IRIS_WEB_MVC_PORT}"
	@echo; echo
	curl "http://localhost:${IRIS_WEB_MVC_PORT}/json"
	@echo; echo
	curl "http://localhost:${IRIS_WEB_MVC_PORT}/hallo"


# ####################################
# Misc AREA
# ####################################
run-web-mvc-favicon:
	md5sum ../src/web/static/favicons/favicon.ico
	curl -s localhost:${IRIS_WEB_MVC_PORT}/favicon.ico | md5sum
