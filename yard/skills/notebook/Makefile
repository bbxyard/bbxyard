# ####################################
# Jupyter
# ####################################


# ####################################
# Dashboard
# ####################################
# 笔记本
notebook:
	/var/x/ac3/bin/jupyter notebook $(pwd)



# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.bak *.log
	-find . -type d -name __pycache__ -exec rm -rvf {} \;

# 确保目录存在
define verifyDir
	[ -d "$(1)" ] || mkdir -p "$(1)"
endef

# 安全移除目录
define safeRmDir
	[ ! -d "$(1)" ] || rm -rvf "$(1)"
endef
