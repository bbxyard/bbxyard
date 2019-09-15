DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ifdef MSG
	GUP_MSG = "$(MSG)"
endif

# ####################################
# Dashboard AREA
# ####################################
install:
	mvn clean install -DskipTests


# ####################################
# git
# ####################################
gpom:
	git add .
	git commit -am $(GUP_MSG)
	git push origin master
	git status
gfom:
	git pull origin master
gs:
	git status
ga:
	git add .


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
	mvn clean

purge:
	rm -rvf ~/.m2/repository/com/bbxyard/mp
