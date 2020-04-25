
# ####################################
# GRPC DEMO
# ####################################


# ####################################
# Dashboard AREA
# ####################################

gen:
	make -C examples/helloworld init gen

# ####################################
# Init AREA
# ####################################
init:
	[ -f go.mod ] || go mod init grpc-demo
	go get -u google.golang.org/grpc@v1.28.1


# ####################################
# utis AREA
# ####################################
purge: clean
	rm -rvf .idea
clean:
	rm -rvf go.sum
	make -C examples/helloworld clean
