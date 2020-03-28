FROM jboss/wildfly:19.0.0.Final

USER root


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
