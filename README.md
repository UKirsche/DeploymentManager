# DeploymentManager

##Datasource
~~~
<datasource jndi-name="java:jboss/datasources/deployment-manager" pool-name="deployment-manager" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
    <connection-url>jdbc:postgresql://localhost:5432/postgres</connection-url>
    <driver>postgresql</driver>
    <security>
        <user-name>docker</user-name>
        <password>docker</password>
    </security>
</datasource>

<drivers>
    <driver name="postgresql" module="org.postgresql">
        <driver-class>org.postgresql.Driver</driver-class>
    </driver>
</drivers>
~~~
sxs
----