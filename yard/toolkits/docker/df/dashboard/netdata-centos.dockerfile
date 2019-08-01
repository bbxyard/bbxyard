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
RUN yum -y install epel-release \
    && yum -y install autoconf automake curl gcc git libmnl-devel libuuid-devel openssl-devel libuv-devel lz4-devel Judy-devel lm_sensors make MySQL-python nc pkgconfig python python-psycopg2 PyYAML zlib-devel

# netdata
RUN cd /usr/src && git clone https://github.com/netdata/netdata.git --depth=100 \
    && cd /usr/src/netdata \
    && ./netdata-installer.sh --dont-start-it --dont-wait --disable-go --install /opt

# script
RUN curl "https://raw.githubusercontent.com/0a0a/docker/master/bin/start-netdata.sh?ver=20190801" -o /usr/local/bin/start-netdata.sh \
    && chmod +x /usr/local/bin/start-netdata.sh

# 端口
EXPOSE 19999

# CMD
# ENTRYPOINT [ "/bin/bash", "/usr/local/bin/start-netdata.sh" ]
# CMD /usr/sbin/init
CMD /usr/local/bin/start-netdata.sh
