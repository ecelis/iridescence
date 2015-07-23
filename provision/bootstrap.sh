#!/bin/bash
export LEIN_ROOT=1
yum -y update
#yum -y swap -- remove fakesystemd -- install systemd systemd-libs
#yum -y update; yum clean all; \
#(cd /lib/systemd/system/sysinit.target.wants/; for i in *; do [ $i == systemd-tmpfiles-setup.service ] || rm -f $i; done); \
#rm -f /lib/systemd/system/multi-user.target.wants/*;\
#rm -f /etc/systemd/system/*.wants/*;\
#rm -f /lib/systemd/system/local-fs.target.wants/*; \
#rm -f /lib/systemd/system/sockets.target.wants/*udev*; \
#rm -f /lib/systemd/system/sockets.target.wants/*initctl*; \
#rm -f /lib/systemd/system/basic.target.wants/*;\
#rm -f /lib/systemd/system/anaconda.target.wants/*; 
yum -y install postgresql postgresql-contrib postgresql-devel mariadb \
  mariadb-devel java-1.7.0-openjdk java-1.7.0-openjdk-devel
yum -y groupinstall 'Development Tools'
yum -y clean all
cd /usr/bin
curl -o lein https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
cd /usr/bin/
chmod +x /usr/bin/lein
/usr/bin/lein
mkdir /clojure
chown -R vagrant /clojure
