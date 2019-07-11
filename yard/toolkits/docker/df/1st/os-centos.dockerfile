# IMG
FROM reg.qiniu.com/library/centos

# WHO
MAINTAINER boxu@yvhai.com

# Change Repo
RUN mv /etc/yum.repos.d/ /etc/yum.repos.d.bak/ \
    && [ ! -d /etc/yum.repos.d/ ] && mkdir -p /etc/yum.repos.d/ \
    && sed -i '/^plugins/c\\plugins=0' /etc/yum.conf \
    && curl http://mirrors.163.com/.help/CentOS7-Base-163.repo -o /etc/yum.repos.d/CentOS7-Base-163.repo

# Update Repo
RUN rm -rf /var/cache/yum && yum clean all && yum makecache \
    && ls -lhrt /etc/yum.repos.d/ \
    && yum -y update \
    && rm -rvf /etc/yum.repos.d/* \
    && curl http://mirrors.163.com/.help/CentOS7-Base-163.repo -o /etc/yum.repos.d/CentOS7-Base-163.repo \
    && yum -y update \
    && ls -lhrt /etc/yum.repos.d/ \
    && yum -y install tree \
    && yum -y install net-snmp-utils net-tools bind-utils iputils telnet lsof \
    && yum clean all && rm -rf /var/cache/yum

# Install Spec

# CMD
CMD /bin/bash
