hallo:
	echo "hallo"

# ################
# Init
# ################
init: init-box init-ref
init-ref:
	-git remote add -f cbox git@github.com:0a0a/cbox.git
	-git remote add -f jbox git@github.com:0a0a/jbox.git
	-git remote add -f nbox git@github.com:0a0a/nbox.git
	-git remote add -f pbox git@github.com:0a0a/pbox.git
	-git remote add -f wbox git@github.com:0a0a/wbox.git
	-git remote add -f ybox git@github.com:0a0a/ybox.git
init-study:
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
