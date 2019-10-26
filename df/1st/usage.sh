# 导入

# docker build --rm=true -t "os-c7${TAG:-:0.1}" - < "../df/1st/os-centos.dockerfile"

docker build --rm=true -t "os-apk${TAG:-:0.1}" https://raw.githubusercontent.com/0a0a/docker/master/df/1st/os-alpine.dockerfile
docker build --rm=true -t "os-u18${TAG:-:0.1}" https://raw.githubusercontent.com/0a0a/docker/master/df/1st/os-ubuntu.dockerfile
docker build --rm=true -t "os-c7${TAG:-:0.1}" https://raw.githubusercontent.com/0a0a/docker/master/df/1st/os-centos.dockerfile

docker build --rm=true -t "bash-apk${TAG:-:0.1}" https://raw.githubusercontent.com/0a0a/docker/master/df/1st/bash-alpine.dockerfile

docker build --rm=true -t "node-apk-dev${TAG:-:0.1}" https://raw.githubusercontent.com/0a0a/docker/master/df/1st/node-alpine-dev.dockerfile
docker build --rm=true -t "node-u18-dev${TAG:-:0.1}" https://raw.githubusercontent.com/0a0a/docker/master/df/1st/node-ubuntu-dev.dockerfile
# docker build --rm=true -t "node-c7-dev${TAG:-:0.1}" https://raw.githubusercontent.com/0a0a/docker/master/df/1st/node-centos-dev.dockerfile
