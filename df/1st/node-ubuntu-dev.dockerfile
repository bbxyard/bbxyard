# IMG
FROM reg.qiniu.com/library/ubuntu:18.04

# WHO
MAINTAINER boxu@yvhai.com

# Install
RUN sed -i 's/http:\/\/archive\.ubuntu\.com\/ubuntu\//http:\/\/mirrors\.163\.com\/ubuntu\//g' /etc/apt/sources.list
RUN apt-get update -qq \
    && apt-get install -y --assume-yes tree vim wget screen git \
    && apt-get install -y python build-essential g++ \
    && wget -O- https://npm.taobao.org/mirrors/node/v8.16.0/node-v8.16.0-linux-x64.tar.gz | tar -zxf - -C /opt/ \
    && echo "export PATH=~/bin:/opt/node-v8.16.0-linux-x64/bin:\$PATH" >> ~/.bashrc \
    && apt-get clean

RUN export PATH=~/bin:/opt/node-v8.16.0-linux-x64/bin:$PATH \
    && npm install -g cnpm --registry=https://registry.npm.taobao.org \
    && cnpm install -g hexo-cli

# CMD
CMD /bin/bash
