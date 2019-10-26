# OS 最小系统
FROM reg.qiniu.com/library/alpine

# WHO
MAINTAINER boxu@yvhai.com

# install
RUN sed -i 's:dl-cdn.alpinelinux.org:mirrors.aliyun.com:g' /etc/apk/repositories
RUN apk update \
    && apk add bash && apk add bash-completion && apk add tree \
    && apk add nodejs && apk add npm

# CMD
CMD [ "/bin/bash" ]
