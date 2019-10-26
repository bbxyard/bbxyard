# IMG
FROM reg.qiniu.com/library/ubuntu:18.04

# WHO
MAINTAINER boxu@yvhai.com

# Install
RUN sed -i 's/http:\/\/archive\.ubuntu\.com\/ubuntu\//http:\/\/mirrors\.163\.com\/ubuntu\//g' /etc/apt/sources.list
RUN apt-get update -qq \
    && apt-get upgrade -y \
    && apt-get install -y vim tree git \
    && apt-get install -y net-tools dnsutils iputils-ping \
    && apt-get clean

# CMD
CMD /bin/bash
