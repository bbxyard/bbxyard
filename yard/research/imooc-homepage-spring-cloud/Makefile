DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ifdef MSG
	GUP_MSG = "$(MSG)"
endif

HOST := server1


# ####################################
# Dashboard AREA
# ####################################
start-eureka-dev:
	java -jar homepage-eureka/target/homepage-eureka-0.0.1-SNAPSHOT.jar
start-eureka:
	java -jar homepage-eureka/target/homepage-eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=$(HOST)

build:
	mvn clean package -Dmaven.test.skip=true -U


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
	rm -rvf *.bak *.log *.tmp
	mvn clean
