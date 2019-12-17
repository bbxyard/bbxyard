# ####################################
# socat用法荟萃
# ####################################


# ####################################
# Install AREA
# ####################################
socat-install-mac:
	brew install socat
socat-install-apt:
	sudo apt get install -y socat
socat-install-yum:
	sudo yum install -y socat


# ####################################
# File - File AREA
# ####################################
socat-as-cat:
	socat - ~/.bashrc
socat-as-copy:
	echo -e "line1\nline2\n" | socat - /tmp/socat-out.txt
	cat /tmp/socat-out.txt


# ####################################
# Server AREA
# ####################################
socat-echo-server:
	socat tcp-listen:${PORT} -
socat-echo-server-transfer-file:
	socat tcp-listen:${PORT} ~/.bashrc
socat-remote-shell:
	socat tcp-listen:${PORT} exec:/bin/bash


# ####################################
# Client AREA
# ####################################
# like telnet ${HOST} ${PORT}
# like nc ${HOST} ${PORT}
socat-as-telent:
	socat - tcp:${HOST}:${PORT}

socat-as-save-file:
	socat /tmp/1.txt tcp:${HOST}:${PORT}


# ####################################
# 端口转发 AREA
# ####################################
socat-fwd:
	socat tcp-listen:${LOCAL_PORT},fork tcp:${HOST}:${PORT}

socat-fwd-test:
	nc ${HOST} ${PORT}


# ####################################
# Command as Server AREA
# ####################################
socat-run-shell: socat-remote-shell


# ####################################
# Ref - socat AREA
# ####################################
# https://blog.csdn.net/qq_17204441/article/details/89324620
# https://www.jianshu.com/p/54005e3095f3
# http://brieflyx.me/2015/linux-tools/socat-introduction/
# socat  -d -d -lf /var/log/socat.log TCP4-LISTEN:15672,bind=192.168.1.252,reuseaddr,fork TCP4:172.17.0.15:15672
