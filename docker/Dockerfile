# "ported" by Adam Miller <maxamillion@fedoraproject.org> from
#   https://github.com/fedora-cloud/Fedora-Dockerfiles
#
# Originally written for Fedora-Dockerfiles by
#   scollier <scollier@redhat.com>

FROM centos:centos6
MAINTAINER Ernesto Celis <ecelis@ecaresoft.com.mx>

RUN yum -y update; yum clean all
RUN yum -y install epel-release; yum clean all
RUN yum -y install tar java-1.8.0-openjdk java-1.8.0-openjdk-devel openssh-server postgresql-server postgresql postgresql-contrib supervisor; yum clean all
## PostgreSQL
ADD ./postgresql-setup /usr/bin/postgresql-setup
ADD ./postgres_user.sh /postgres_user.sh
ADD ./supervisord.conf /etc/supervisord.conf
ADD ./start_postgres.sh /start_postgres.sh

RUN chmod +x /usr/bin/postgresql-setup
RUN chmod +x /start_postgres.sh
RUN chmod +x /postgres_user.sh

RUN mkdir /var/run/sshd
RUN ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key -N ''
RUN /usr/bin/postgresql-setup initdb

ADD ./postgresql.conf /var/lib/pgsql/data/postgresql.conf
## PLJAVA
RUN curl -o /tmp/pljava.tar.gz https://v3ctor.club/~ecelis/pljava/pljava-pg8.4-x86_64.tar.gz
RUN cd /usr/share; tar -tvzf /tmp/pljava.tar.gz ; rm /tmp/pljava.tar.gz

RUN chown -v postgres.postgres /var/lib/pgsql/data/postgresql.conf

RUN echo "host    all             all             0.0.0.0/0               md5" >> /var/lib/pgsql/data/pg_hba.conf

RUN /postgres_user.sh

EXPOSE 22 5432

CMD ["/bin/bash", "/start_postgres.sh"]
