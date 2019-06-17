.PHONY: clean all

VENV_HOME	:= .venv
PIP3	:= $(VENV_HOME)/bin/pip3
PY3		:= $(VENV_HOME)/bin/python3


# ####################################
# Dashboard AREA
# ####################################
start: deps

ar:
	$(PY3) archive.py

# ####################################
# Deps AREA
# ####################################
deps: $(VENV_HOME) http-libs

$(VENV_HOME):
	virtualenv -p $(shell which python3) $@

http-libs:
	$(PIP3) install requests
	$(PIP3) install httplib2


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
	rm -rvf 'https:/'
