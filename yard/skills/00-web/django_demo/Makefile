.PHONY: clean all

PIP_REPO_OPT = -i http://pypi.douban.com/simple --trusted-host pypi.douban.com

PORT 	:= 8080
CTL		:= python3 manage.py

VENV	:= py3-django-xadmin


FIXTURE_FILE = log/fixture.json
FIXTURE_PRETTY_FILE = log/fixture-pretty.json


# ####################################
# Dashboard AREA
# ####################################
start:
	supervisord -c conf/supervisord.conf
stop:
ctl:
	supervisorctl -c conf/supervisord.c


# ####################################
# Dashboard - Debug AREA
# ####################################
start-fg:
	$(CTL) runserver $(PORT)

migrate: db-gen

shell:
	$(CTL) shell


# ####################################
# Admin AREA
# ####################################
admin-init:
	$(CTL) createsuperuser
admin-change-password:
	$(CTL) changepassword


# ####################################
# Data AREA
# ####################################
db-shell:
	$(CTL) dbshell
db-gen:
	$(CTL) makemigrations
	$(CTL) migrate
db-dump: log
	$(CTL) dumpdata -o $(FIXTURE_FILE)
	$(CTL) dumpdata --indent 2 -o $(FIXTURE_PRETTY_FILE)
db-load: log
	$(CTL) loaddata $(FIXTURE_FILE)
db-orm-import:
	python3 orm_data.py


# ####################################
# Create AREA
# ####################################
blog:
	$(CTL) startapp $@


# ####################################
# Django Evn AREA
# ####################################
verify-venv:
	workon | grep $(VENV) || mkvirtualenv $(VENV)

verify-venv-deps:
	pip3 install $(PIP_REPO_OPT) celery django redis supervisor
	pip3 install pillow
	pip3 install https://codeload.github.com/sshwsfc/xadmin/zip/django2
	pip3 list
	# pip3 install -r https://raw.githubusercontent.com/sshwsfc/xadmin/master/requirements.txt


# ####################################
# Deps AREA
# ####################################
deps: conf log

conf:
	mkdir -p $@
	echo_supervisord_conf > $@/supervisord.conf
log:
	mkdir -p $@

py3-celery4-dj2:
	pip3 install $(PIP_REPO_OPT) celery django redis supervisor
	pip3 list


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
