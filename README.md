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

### Neues Tag abfragen
~~~
stage('Push image to registry') {
    when {
        expression { return params.PUSH }
    }
    environment {
        DEPLOYMENT_MANAGER ="http://etw-docker-03.bvaetw.de:8088/deployment-manager/api"
        DEPLOYMENT_MANAGER_URL = "${env.DEPLOYMENT_MANAGER}/projects/${env.DEPLOYMENT_MANAGER_PROJECT}/images"
        DEPLOYMENT_MANAGER_PROJECT = "manager"
        IMAGE = "http://etw-docker-03.bvaetw.de/manager/manager"
                

        TAG =  sh ( script: "curl --header \"Content-Type: application/json\" \\\n" +
                "  --request POST \\\n" +
                "  --data '{\n" +
                "  \"user\": \"${env.USER}\",\n" +
                "  \"image\": \"${env.IMAGE}\",\n" +
                "  \"version\":  \"${env.VERSION}\"\n" +
                "}' \\\n" +
                "  ${env.DEPLOYMENT_MANAGER_URL}",
                returnStdout: true )
    }
    steps {
       // Baut das docker Image
       echo "PUSH"
       }
    }
}

~~~