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

office:
	$(PY3) io_fmt_csv.py


# ####################################
# Deps AREA
# ####################################
deps: $(VENV_HOME) http-libs office-libs db-libs

$(VENV_HOME):
	virtualenv -p $(shell which python3) $@

http-libs:
	$(PIP3) install requests
	$(PIP3) install httplib2

office-libs:
	$(PIP3) install pandas
	$(PIP3) install openpyxl

db-libs:
	$(PIP3) install redis
	$(PIP3) install pymongo
	$(PIP3) install pymysql
	$(PIP3) install mysql-connector


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
	rm -rvf 'https:/'
	[ -d out ] && rm -rvf out || >/dev/null
