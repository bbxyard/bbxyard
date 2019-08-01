# mk大总管
URL_BASE := https://raw.githubusercontent.com/0a0a/docker/master/usage
DL_SCP   := download-all-ctl-mk-in-cur-dir.sh

MK_LIST	:= $(shell ls *.mk)


build-all:
	for x in $(MK_LIST); do \
		make -f $$x build; \
	done

gen-download-scp:
	> $(DL_SCP)
	for x in $(MK_LIST); do \
		echo "curl $(URL_BASE)/$$x -o $$x" >> $(DL_SCP); \
	done
	chmod +x $(DL_SCP)
	cat -n $(DL_SCP)
