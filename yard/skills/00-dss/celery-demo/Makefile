.PHONY: clean all


# ####################################
# Dashboard AREA
# ####################################
start-worker:
	celery worker -A 'celery_app' -l info


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
