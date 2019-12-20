# ####################################
# 测试样例
# ####################################

BEEGO_PORT := 18081
GIN_PORT   := 18082
IRIS_PORT  := 18084


# ####################################
# Dashboard AREA
# ####################################
start-restful-beego:
	go run $(SRC_DIR)/restful/beego/main.go
start-restful-gin:
	go run $(SRC_DIR)/restful/gin/main.go
start-restful-iris:
	go run $(SRC_DIR)/restful/iris/main.go

run-restful: run-restful-beego-normal-case \
	run-restful-gin-normal-case \
	run-restful-iris-normal-case


# ####################################
# Beego AREA
# ####################################
run-restful-beego-normal-case:
	curl http://localhost:${BEEGO_PORT}/user
	curl -d '' http://localhost:${BEEGO_PORT}/user
	# 正则路由
	curl http://localhost:${BEEGO_PORT}/users/bbxyard31415
	curl http://localhost:${BEEGO_PORT}/re/num/1234
	curl http://localhost:${BEEGO_PORT}/re/num/user5792de
	curl http://localhost:${BEEGO_PORT}/re/alpha/pufan66
	curl http://localhost:${BEEGO_PORT}/re/wild/pufan66@126.com/a/bb/cC/haha
	curl http://localhost:${BEEGO_PORT}/re/path/dir1/path2/folder3/consul-bin.zip
	curl http://localhost:${BEEGO_PORT}/re/path/consul-bin.7z

run-restful-beego-error-case:
	curl http://localhost:${BEEGO_PORT}/re/num/should_be-error
	curl http://localhost:${BEEGO_PORT}/re/alpha/pufan-66

run-restful-beego-warn-case:
	curl http://localhost:${BEEGO_PORT}/re/path/consul-1.6.2.tar.bz2
	curl http://localhost:${BEEGO_PORT}/re/path/microservice/go-micro-1.2.tar


# ####################################
# Gin AREA
# ####################################
run-restful-gin-normal-case:
	curl http://localhost:${GIN_PORT}/user
	curl -d '' http://localhost:${GIN_PORT}/user
	curl http://localhost:${GIN_PORT}/users/bbxyard31415
	# 组路由
	curl http://localhost:${GIN_PORT}/g1/action1
	curl http://localhost:${GIN_PORT}/g1/action2
	curl http://localhost:${GIN_PORT}/g1/action3


# ####################################
# Iris AREA
# ####################################
run-restful-iris-normal-case:
	curl http://localhost:${IRIS_PORT}/user
	curl -d '' http://localhost:${IRIS_PORT}/user
	curl -d '' http://localhost:${IRIS_PORT}/json
