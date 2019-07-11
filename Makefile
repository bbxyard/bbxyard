.PHONY: init clean
all: gpull

BRANCH := master

SUB_SDK_LIST := cbox jbox nbox pbox wbox ybox
SUB_SDK_GIT_PREFIX := git@github.com:0a0a
SUB_SDK_LOCAL_PREFIX := yard/sdk

TOOLKIT_LIST := docker
TOOLKIT_GIT_PREFIX := $(SUB_SDK_GIT_PREFIX)
TOOLKIT_LOCAL_PREFIX := yard/toolkits

REF_LIST := min-cli
REF_GIT_PREFIX := git@github.com:meili
REF_LOCAL_PREFIX := ref


DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG  = "Auto Commited at $(DATA_SUF)"
DEPLOY_ENV =

SUB_LIST = yh-os-init yh-user yh-brew yh-mongo \
	  yh-run-clean yh-nodejs \
	  yh-ssh-key

ifdef MSG
	GUP_MSG = "$(MSG)"
endif

ifdef HOST
	DEPLOY_ENV = THIS_HOST_NAME=$(HOST)
endif


# ####################################
# Dashboard AREA
# ####################################

# ################
# Init
# ################
init: init-box init-ref
init-box:
	-git remote add -f cbox git@github.com:0a0a/cbox.git
	-git remote add -f jbox git@github.com:0a0a/jbox.git
	-git remote add -f nbox git@github.com:0a0a/nbox.git
	-git remote add -f pbox git@github.com:0a0a/pbox.git
	-git remote add -f wbox git@github.com:0a0a/wbox.git
	-git remote add -f ybox git@github.com:0a0a/ybox.git
init-ref:
	-git remote add -f min-cli git@github.com:meili/min-cli.git

# ########################
# Ctrl
# ########################
gadd: ref-add-all box-add-all
gpull: ref-pull-all box-pull-all
	git pull origin master
gpush: ref-push-all box-push-all
	git push origin master

ref-add-all: ref-min-cli-add
ref-pull-all: ref-min-cli-pull
ref-push-all: ref-min-cli-push

box-add-all: cbox-add jbox-add nbox-add pbox-add wbox-add ybox-add
box-pull-all: cbox-pull jbox-pull nbox-pull pbox-pull wbox-pull ybox-pull
box-push-all: cbox-push jbox-push nbox-push pbox-push wbox-push ybox-push

#####################################################
# SUB TREE OPERATION TEMPLATE
#####################################################
ref-%-add:
	-git subtree add --prefix=ref/$< $< master --squash
ref-%-pull:
	git subtree pull --prefix=ref/$< $< master --squash
ref-%-push:

%box-add:
	-git subtree add --prefix=yard/sdk/$< $< master --squash
%box-pull:
	git subtree pull --prefix=yard/sdk/$< $< master --squash
%box-push:
	git subtree push --prefix=yard/sdk/$< $< master

#####################################################
# ref-min-cli
#####################################################
ref-min-cli-all: ref-min-cli-add ref-min-cli-pull ref-min-cli-push
ref-min-cli-add: min-cli
ref-min-cli-pull: min-cli
ref-min-cli-push: min-cli
min-cli:

#####################################################
# cbox
#####################################################
cbox-all: cbox-add cbox-pull cbox-push
cbox-add: cbox
cbox-pull: cbox
cbox-push: cbox
cbox:

#####################################################
# jbox
#####################################################
jbox-all: jbox-add jbox-pull jbox-push
jbox-add: jbox
jbox-pull: jbox
jbox-push: jbox
jbox:

#####################################################
# nbox
#####################################################
nbox-all: nbox-add nbox-pull nbox-push
nbox-add: nbox
nbox-pull: nbox
nbox-push: nbox
nbox:

#####################################################
# pbox
#####################################################
pbox-all: pbox-add pbox-pull pbox-push
pbox-add: pbox
pbox-pull: pbox
pbox-push: pbox
pbox:

#####################################################
# wbox
#####################################################
wbox-all: wbox-add wbox-pull wbox-push
wbox-add: wbox
wbox-pull: wbox
wbox-push: wbox
wbox:

#####################################################
# ybox
#####################################################
ybox-all: ybox-add ybox-pull ybox-push
ybox-add: ybox
ybox-pull: ybox
ybox-push: ybox
ybox:


# ####################################
# git
# ####################################
gpom:
	git add .
	git commit -am $(GUP_MSG) || >/dev/null
	git push origin master
	git status
gs:
	git status
ga:
	git add .
gpull-self:
	git pull
gpush-self: gpom


# gpull:  gpull-self gpull-sub-box gpull-deploy
# gpull-sub-box:
# 	for x in $(SUB_LIST); do \
# 		grep "$$x" .git/config >/dev/null || git remote add -f $$x $(SUB_SDK_GIT_PREFIX)/$$x.git; \
# 		[ ! -d "roles/$$x" ] && git subtree add --prefix=roles/$$x $$x $(BRANCH) --squash || >/dev/null; \
# 		$(call doSubPull,$$x); \
# 	done;
# gpull-deploy:
# 	grep "yh-roles-deploy" .git/config >/dev/null || git remote add -f yh-roles-deploy $(SUB_SDK_GIT_PREFIX)/yh-roles-deploy.git; \
# 	[ ! -d "deploy" ] && git subtree add --prefix=deploy yh-roles-deploy $(BRANCH) --squash || >/dev/null; \
# 	git subtree pull --prefix=deploy yh-roles-deploy $(BRANCH) --squash

ginit:
	$(call doSubListInit,$(SUB_SDK_LIST),$(SUB_SDK_LOCAL_PREFIX),$(SUB_SDK_GIT_PREFIX))
	$(call doSubListInit,$(TOOLKIT_LIST),$(TOOLKIT_LOCAL_PREFIX),$(TOOLKIT_GIT_PREFIX))
	$(call doSubListInit,$(REF_LIST),$(REF_LOCAL_PREFIX),$(REF_GIT_PREFIX))

gpull: gpull-self ginit
gpush: gpom ginit

# gpush: gpush-self gpush-sub gpush-deploy
# gpush-sub:
# 	for x in $(SUB_LIST); do \
# 		grep "$$x" .git/config >/dev/null || git remote add -f $$x $(SUB_SDK_GIT_PREFIX)/$$x.git; \
# 		[ ! -d "roles/$$x" ] && git subtree add --prefix=roles/$$x $$x $(BRANCH) --squash || >/dev/null; \
# 		$(call doSubPush,$$x); \
# 	done;
# gpush-deploy:
# 	grep "yh-roles-deploy" .git/config >/dev/null || git remote add -f yh-roles-deploy $(SUB_SDK_GIT_PREFIX)/yh-roles-deploy.git; \
# 	[ ! -d "deploy" ] && git subtree add --prefix=deploy yh-roles-deploy $(BRANCH) --squash || >/dev/null; \
# 	git subtree push --prefix=deploy yh-roles-deploy $(BRANCH) || >/dev/null


# ####################################
# Utils AREA
# ####################################
define doSubListInit
	for x in $(1); do \
		grep "$$x" .git/config >/dev/null || git remote add -f $$x $(3)/$$x.git	\
		[ ! -d "$(2)/$$x" ] && git subtree add --prefix=$(2)/$$x $$x $(BRANCH) --squash || >/dev/null	\
	done;
endef

define doSubListPull
	for x in $(1); do \
		git subtree pull --prefix=$(2)/$$x $$x $(BRANCH) --squash; \
	done;
endef

define doSubListPush
	for x in $(1); do \
		git subtree push --prefix=$(2)/$$x $$x $(BRANCH) || >/dev/null; \
	done;
endef


# define doSubInit
# 	grep "$(1)" .git/config >/dev/null || git remote add -f $(1) git@github.com:a0a0/$(1).git
# 	[ ! -d "$(2)" ] && git subtree add --prefix=$(2) $(1) $(BRANCH) --squash || >/dev/null
# endef

# define doSubPull
# 	git subtree pull --prefix=$(2) $(1) $(BRANCH) --squash
# endef

# define doSubPush
# 	git subtree push --prefix=$(2) $(1) $(BRANCH) || >/dev/null
# endef


# ####################################
# Fini AREA
# ####################################
clean:
	rm -rvf *.bak *.log
