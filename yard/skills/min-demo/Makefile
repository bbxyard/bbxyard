DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ifdef MSG
	GUP_MSG = "$(MSG)"
endif

# ####################################
# Dashboard Area
# ####################################
build:
	min build
dev:
	min dev

# ####################################
# git
# ####################################
gpush:
	git add .
	git commit -am $(GUP_MSG)
	git push
	git status
gpull:
	git pull
gs:
	git status
ga:
	git add .
