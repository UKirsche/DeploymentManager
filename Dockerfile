FROM jboss/wildfly:18.0.1.Final

USER root

ENV https_proxy 10.12.0.56:8009
ENV http_proxy 10.12.0.56:8009
ENV no_proxy 10.12.105.70, 10.12.105.72, etw-docker-01.bvaetw.de, .bvaetw.de

RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/*
RUN chown -R jboss:jboss /opt/jboss/wildfly/

ENV POSTGRESQL_VERSION 42.2.11

USER jboss
ADD .build/ /tmp/

RUN cd /tmp && \
  curl --location --output postgresql.jar --url https://jdbc.postgresql.org/download/postgresql-${POSTGRESQL_VERSION}.jar && \
  /opt/jboss/wildfly/bin/jboss-cli.sh --file=/tmp/standalone.cli && \
  rm /tmp/postgresql.jar

ADD target/deployment-manager.war /opt/jboss/wildfly/standalone/deployments/
