.PHONY: clean all


# ####################################
# Dashboard AREA
# ####################################
start-worker:
	celery worker -A 'celery_app' -l info

start-beat:
	celery beat -A 'celery_app' -l info


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
