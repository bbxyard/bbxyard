DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ifdef MSG
	GUP_MSG = "$(MSG)"
endif


# ####################################
# Dashboard AREA
# ####################################
init: init-seckill


# ####################################
# Init AREA
# ####################################
init-seckill:
	mvn archetype:generate -DgroupId=com.bbxyard.mall -DartifactId=seckill -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-webapp


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
	rm -rvf *.class
