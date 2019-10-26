FROM reg.qiniu.com/library/alpine

# WHO
MAINTAINER boxu@yvhai.com

# install
RUN sed -i 's:dl-cdn.alpinelinux.org:mirrors.aliyun.com:g' /etc/apk/repositories
RUN apk update && apk add bash && apk add bash-completion

# CMD
CMD [ "/bin/bash" ]
