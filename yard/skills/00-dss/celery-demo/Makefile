.PHONY: clean all

PIP_REPO_OPT = -i http://pypi.douban.com/simple --trusted-host pypi.douban.com


# ####################################
# Dashboard AREA
# ####################################
start:
	supervisord -c conf/supervisord.conf
stop:
ctl:
	supervisorctl -c conf/supervisord.conf


# ####################################
# Dashboard - Debug AREA
# ####################################
start-fg:
	supervisord -c conf/supervisord.conf -n
start-fg-worker:
	celery worker -A 'celery_app' -l INFO -n 'worker-01@%h'
start-fg-beat:
	celery beat -A 'celery_app' -l info


# ####################################
# Deps AREA
# ####################################
deps:
	install -d conf log
	echo_supervisord_conf > conf/supervisord.conf

	
py3-celery4-dj2:
	pip3 install $(PIP_REPO_OPT) celery django redis supervisor
	pip3 list


# ####################################
# Utils AREA
# ####################################
clean:
	-rm -rvf *.bak *.log celerybeat-schedule log/*

purge: clean
	rm -rvf .idea
