.PHONY: clean all

PORT 	:= 8080
CTL		:= python3 manage.py

# ####################################
# Dashboard AREA
# ####################################
start:
	$(CTL) runserver $(PORT)

migrate:
	$(CTL) makemigrations
	$(CTL) migrate


# ####################################
# Create AREA
# ####################################
blog:
	$(CTL) startapp $@


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
