.PHONY: clean all

PORT 	:= 8080
CTL		:= python3 manage.py

VENV	:= py3-django-xadmin


FIXTURE_FILE = log/fixture.json
FIXTURE_PRETTY_FILE = log/fixture-pretty.json


# ####################################
# Dashboard AREA
# ####################################
start:
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
log:
	mkdir -p $@
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
	pip3 install pillow
	pip3 install https://codeload.github.com/sshwsfc/xadmin/zip/django2
	# pip3 install -r https://raw.githubusercontent.com/sshwsfc/xadmin/master/requirements.txt


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
