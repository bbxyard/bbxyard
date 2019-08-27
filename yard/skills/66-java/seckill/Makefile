DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ifdef MSG
	GUP_MSG = "$(MSG)"
endif


# ####################################
# Dashboard AREA
# ####################################
build:
	mvn compile

jar:
	mvn jar:jar

pub:
	mvn package
	mvn install


# ####################################
# Init AREA
# ####################################
depress:
	mvn eclipse:eclipse
	mvn idea:idea


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
	rm -rvf target/*
	rm -rvf *.class
