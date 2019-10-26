.PHONY: init clean
all: gpull

BRANCH := master

SUB_SDK_LIST := cbox jbox nbox pbox wbox ybox
SUB_SDK_GIT_PREFIX := git@github.com:0a0a
SUB_SDK_LOCAL_PREFIX := yard/sdk

TOOLKIT_LIST := docker
TOOLKIT_GIT_PREFIX := $(SUB_SDK_GIT_PREFIX)
TOOLKIT_LOCAL_PREFIX := yard/toolkits

TEMPLATE_LIST := hbx-demos
TEMPLATE_GIT_PREFIX := $(SUB_SDK_GIT_PREFIX)
TEMPLATE_LOCAL_PREFIX := template/web

REF_LIST := min-cli
REF_GIT_PREFIX := git@github.com:yh24
REF_LOCAL_PREFIX := ref


DATA_SUF = $(shell date +"%Y.%m.%d.%H.%M.%S")
GUP_MSG  = "Auto Commited at $(DATA_SUF)"
DEPLOY_ENV =


ifdef MSG
	GUP_MSG = "$(MSG)"
endif

ifdef HOST
	DEPLOY_ENV = THIS_HOST_NAME=$(HOST)
endif


# ####################################
# Dashboard AREA
# ####################################



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


ginit:
	$(call doSubListInit,$(SUB_SDK_LIST),$(SUB_SDK_LOCAL_PREFIX),$(SUB_SDK_GIT_PREFIX))
	$(call doSubListInit,$(TOOLKIT_LIST),$(TOOLKIT_LOCAL_PREFIX),$(TOOLKIT_GIT_PREFIX))
	$(call doSubListInit,$(TEMPLATE_LIST),$(TEMPLATE_LOCAL_PREFIX),$(TEMPLATE_GIT_PREFIX))
	$(call doSubListInit,$(REF_LIST),$(REF_LOCAL_PREFIX),$(REF_GIT_PREFIX))

gpull: gpull-self ginit
	$(call doSubListPull,$(SUB_SDK_LIST),$(SUB_SDK_LOCAL_PREFIX),$(SUB_SDK_GIT_PREFIX))
	$(call doSubListPull,$(TOOLKIT_LIST),$(TOOLKIT_LOCAL_PREFIX),$(TOOLKIT_GIT_PREFIX))
	$(call doSubListPull,$(TEMPLATE_LIST),$(TEMPLATE_LOCAL_PREFIX),$(TEMPLATE_GIT_PREFIX))
	$(call doSubListPull,$(REF_LIST),$(REF_LOCAL_PREFIX),$(REF_GIT_PREFIX))

gpush: gpom ginit
	$(call doSubListPush,$(SUB_SDK_LIST),$(SUB_SDK_LOCAL_PREFIX),$(SUB_SDK_GIT_PREFIX))
	$(call doSubListPush,$(TOOLKIT_LIST),$(TOOLKIT_LOCAL_PREFIX),$(TOOLKIT_GIT_PREFIX))
	$(call doSubListPush,$(TEMPLATE_LIST),$(TEMPLATE_LOCAL_PREFIX),$(TEMPLATE_GIT_PREFIX))
	# $(call doSubListPush,$(REF_LIST),$(REF_LOCAL_PREFIX),$(REF_GIT_PREFIX))


# ####################################
# Utils AREA
# ####################################
define doSubListInit
	for x in $(1); do \
		grep "$$x" .git/config >/dev/null || git remote add -f $$x $(3)/$$x.git;	\
		[ ! -d "$(2)/$$x" ] && git subtree add --prefix=$(2)/$$x $$x $(BRANCH) --squash || >/dev/null; \
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


# ####################################
# Fini AREA
# ####################################
clean:
	rm -rvf *.bak *.log
