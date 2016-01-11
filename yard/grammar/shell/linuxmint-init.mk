all: edit dev input
edit:
	echo y | apt-get install vim
dev:
	echo y | apt-get install build-essential
	echo y | apt-get install git subversion subversion-tools
	echo y | apt-get install ant maven
	echo y | apt-get install anjuta
input:
	echo y | apt-get install fcitx fcitx-pinyin fcitx-table-wubi 
