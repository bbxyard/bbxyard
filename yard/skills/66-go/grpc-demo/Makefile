
# ####################################
# GRPC DEMO
# ####################################


# ####################################
# Dashboard AREA
# ####################################

gen:
	make -C examples/helloworld

# ####################################
# utis AREA
# ####################################
purge: clean
	rm -rvf .idea
clean:
	rm -rvf go.sum
	make -C examples/helloworld clean
