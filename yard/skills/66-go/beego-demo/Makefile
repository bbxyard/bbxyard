DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG = "Auto Commited at $(DATA_SUF)"

ifdef MSG
	GUP_MSG = "$(MSG)"
endif


# ####################################
# Dashboard AREA
# ####################################


# ####################################
# Kits AREA
# ####################################
gen:
	bee generate scaffold user -fields="id:int64,name:string,gender:int,age:int" -driver=mysql -conn="hero@tcp(127.0.0.1:3307)/imooc_beego"


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
