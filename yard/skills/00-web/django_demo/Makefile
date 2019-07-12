.PHONY: clean all

PORT := 8080


# ####################################
# Dashboard AREA
# ####################################
start:
	python3 manage.py runserver $(PORT)


# ####################################
# Create AREA
# ####################################
blog:
	python3 manage.py startapp $@


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
