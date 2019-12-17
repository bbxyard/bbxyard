# ####################################
# Micro AREA
# ####################################

# enable go modules
export GO111MODULE=on

# ####################################
# CRUD AREA
# ####################################
micro-all:
	micro --local

micro-new:
	micro new example

# run example
micro-run-local:
	micro run example --local

# list services
micro-list:
	micro list services

# call service
micro-call:
	micro call go.micro.srv.example Example.Call '{"name": "yvhai.com"}'

# service info
micro-get:
	micro get service go.micro.srv.example

# ####################################
# Ref AREA
# ####################################
# https://github.com/micro/micro
