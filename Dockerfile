FROM centos:7
ENV LEIN_ROOT=1
VOLUME ["
RUN yum -y update ; yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel

#RUN yum -y update ; yum -y groupinstall 'Development Tools' ; \
#  yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel postgresql \
#  postgresql-contrib postgresql-devel mariadb mariadb-server mariadb-devel
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
WORKDIR /usr/bin
RUN curl -o lein \
  https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein; \
  chmod a+x lein; lein
#mkdir /clojure
#chown -R vagrant /clojure
EXPOSE 3000
#USER vagrant
ENTRYPOINT exec /usr/bin/bash -c /usr/bin/lein repl
WORKDIR /vagrant
#CMD ["sh", "-c", "cd", "/vagrant/webui;", "lein", "ring" "server-headless"]

