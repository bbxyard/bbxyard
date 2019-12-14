# ####################################
# Configure AREA
# ####################################
BIN_DIR   := bin
PB_DIR    := pb


# ####################################
# Dashboard AREA
# ####################################


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
build: build-server build-client
build-server:
	go build  -o bin/hallo-server greeter_server/main.go
build-client:
	go build  -o bin/hallo-client greeter_client/main.go


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
	rm -rvf *.bak *.log
	rm -rvf bin/hallo-* pb/*

