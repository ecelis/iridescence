FROM centos:7
MANTAINER Ernesto Celis <ecelis@ecaresoft.com>

ENV LEIN_ROOT=1

RUN yum -y update ; yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel git

WORKDIR /usr/bin
RUN curl -o lein \
  https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein; \
  chmod a+x lein; lein

WORKDIR /iridescence
RUN ./build
## fuzzy-urls clojure-hl7parser
#mkdir /clojure
#chown -R vagrant /clojure
EXPOSE 3000
#USER vagrant
ENTRYPOINT exec /usr/bin/bash -c /usr/bin/lein repl
WORKDIR /vagrant
#CMD ["sh", "-c", "cd", "/vagrant/webui;", "lein", "ring" "server-headless"]

