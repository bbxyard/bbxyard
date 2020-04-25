# Ref: https://grpc.io/docs/quickstart/go/

# ####################################
# Configure AREA
# ####################################
BIN_DIR   := bin
PB_DIR    := pb


# ####################################
# Dashboard AREA
# ####################################


# ####################################
# Init AREA
# ####################################
init:
	[ -f go.mod ] || go mod init bbxyard.com
	go get -u google.golang.org/grpc@v1.28.1
	go get -u github.com/golang/protobuf


start-server:
	go run greeter_server/main.go

start-client:
	go run greeter_client/main.go $@


# ####################################
# Gen AREA
# ####################################
gen: gen-init gen-pb gen-pb-go
gen-init:
	[ -d $(PB_DIR) ] || mkdir -p $(PB_DIR)
gen-pb:
	protoc --cpp_out=$(PB_DIR) helloworld/helloworld.proto
	protoc --csharp_out=$(PB_DIR) helloworld/helloworld.proto
	protoc --java_out=$(PB_DIR) helloworld/helloworld.proto
	protoc --js_out=$(PB_DIR) helloworld/helloworld.proto
	protoc --objc_out=$(PB_DIR) helloworld/helloworld.proto
	protoc --php_out=$(PB_DIR) helloworld/helloworld.proto
	protoc --python_out=$(PB_DIR) helloworld/helloworld.proto
	protoc --ruby_out=$(PB_DIR) helloworld/helloworld.proto
gen-pb-go:
	# pb/helloworld/helloworld.pb.go
	protoc helloworld/helloworld.proto --go_out=plugins=grpc:pb
	# pb/helloworld.pb.go
	protoc -Ihelloworld/ helloworld/helloworld.proto --go_out=plugins=grpc:pb
	# ./helloworld/helloworld.pb.go
	protoc -Ihelloworld/ helloworld/helloworld.proto --go_out=plugins=grpc:helloworld


# ####################################
# Deploy AREA
# ####################################
build: init gen
	[ -d "$(BIN_DIR)" ] || mkdir -p "$(BIN_DIR)"
	go build -o $(BIN_DIR)/greeter_server greeter_server/main.go
	go build -o $(BIN_DIR)/greeter_client greeter_client/main.go


# ####################################
# Run like Script AREA
# ####################################
run-server:
	go run greeter_server/main.go
run-client:
	go run greeter_client/main.go



# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log go.sum
	rm -rvf bin/hallo-* pb bin

