#FROM centos:7
FROM centos:6
MAINTAINER Ernesto Celis <ecelis@ecaresoft.com>

ENV LEIN_ROOT=1
EXPOSE 3000

RUN yum -y update ; yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel \
  git; \
  rpm -Uvh http://yum.postgresql.org/9.4/redhat/rhel-6-x86_64/pgdg-centos94-9.4-1.noarch.rpm; \
  yum -y install postgresql94 postgresql94-server postgresql94-contrib postgresql94-devel; \
  chkconfig postgresql-9.4 on; service postgresql-9.4 initdb

WORKDIR /usr/bin
RUN curl -o lein \
  https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein; \
  chmod a+x lein; lein; mkdir /clojure

WORKDIR /clojure
RUN git clone -b ecelis https://github.com/ecelis/clojure-hl7-messaging-2-parser.git; \
  git clone https://github.com/ecelis/clojure-fuzzy-urls.git; \
  cd /clojure/clojure-hl7-messaging-2-parser; lein install; \
  cd /clojure/clojure-fuzzy-urls; lein install

#WORKDIR /iridescence
#ENTRYPOINT exec /usr/bin/bash -c cd /iridescence
#CMD ["lein", "clean", "ring" "server-headless"]

