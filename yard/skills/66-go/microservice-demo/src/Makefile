
# 引入依赖
include abc/yvhai/mk/deps.mk

# ####################################
# Configure AREA
# ####################################
BIN_DIR := $(CURDIR)/../bin


# ####################################
# Dashboard AREA
# ####################################




# ####################################
# Build AREA
# ####################################
go-micro: go-micro-web go-micro-server go-micro-client
go-micro-web:
	go build -o $(BIN_DIR)/$@ go-micro/greeter/web/main.go
go-micro-server:
	go build -o $(BIN_DIR)/$@ go-micro/greeter/server/main.go
go-micro-client:
	go build -o $(BIN_DIR)/$@ go-micro/greeter/client/main.go

gizmo: gizmo-server gizmo-client
gizmo-server: $(BIN_DIR)/gizmo-server.json
	go build -o $(BIN_DIR)/$@ gizmo/greeter/server/main.go
gizmo-client:
	go build -o $(BIN_DIR)/$@ gizmo/greeter/client/main.go

$(BIN_DIR)/gizmo-server.json:
	[ -f $@ ] || cp gizmo/greeter/server/gizmo-server.json $@

go-kit: go-kit-server go-kit-client
go-kit-server:
	go build -o $(BIN_DIR)/$@ go-kit/greeter/server/main.go
go-kit-client:
	go build -o $(BIN_DIR)/$@ go-kit/greeter/client/main.go


# ####################################
# Utils AREA
# ####################################
