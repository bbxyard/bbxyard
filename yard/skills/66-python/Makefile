.PHONY: clean

# ####################################
# Dashboard AREA
# ####################################
init: virtualenv python-env python3-env python3-web-env

web-dev: web-flask

# ####################################
# Init AREA
# ####################################
virtualenv:
	pip3 list | grep virtualenv >/dev/null || pip3 install virtualenv
python-env:
	which python && virtualenv python-env || >/dev/null
python3-env:
	which python3 && virtualenv -p $(shell which python3) python3-env || >/dev/null
python3-web-env:
	which python3 && virtualenv -p $(shell which python3) $@ || >/dev/null


# ####################################
# Web Framework AREA
# ####################################
web-flask:
	python3-web-env/bin/pip3 install flask


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf python*-env
