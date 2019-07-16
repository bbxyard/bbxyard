.PHONY: clean all


# ####################################
# Dashboard AREA
# ####################################
start:
	supervisord -c conf/supervisord.conf
stop:


# ####################################
# Dashboard - Debug AREA
# ####################################
start-fg:
	supervisord -c conf/supervisord.conf -n
start-worker:
	celery worker -A 'celery_app' -l info
start-beat:
	celery beat -A 'celery_app' -l info


# ####################################
# Supervisor AREA
# ####################################
conf:
	mkdir -p $@
	echo_supervisord_conf > $@/supervisord.conf
log:
	mkdir -p $@
deps: conf log

pip-deps:
	# pip3 install supervisor


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
