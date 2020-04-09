//noinspection GroovyAssignabilityCheck
pipeline {

    agent any

    parameters {
        string(name: 'TAG', defaultValue: 'latest', description: 'Welcher TAG soll deployed werden?')
        booleanParam(name: 'ETW_DEPLOY', defaultValue: true, description: 'Soll der Service auf ETW deployed werden?')
        booleanParam(name: 'INT_DEPLOY', defaultValue: false, description: 'Soll der Service auf INT deployed werden?')
        booleanParam(name: 'PROD_DEPLOY', defaultValue: false, description: 'Soll der Service auf PROD deployed werden?')
    }


    environment {
        APP_NAME = "dmp"
        SERVICE_PORT = '33389'
        IMAGE = "etw-docker-03.bvaetw.de/dmp/dmp-fachanwendung:${params.TAG}"
        TAG = "${params.TAG}"
        DEPLOYMENT_MANAGER_PROJECT = "dmpfa"
        DEPLOYMENT_MANAGER_URL = "http://etw-docker-03.bvaetw.de:8088/deployment-manager/api/projects/${env.DEPLOYMENT_MANAGER_PROJECT}/images/${env.TAG}/deployed"
    }

    stages {

        stage('Deploy to ETW') {
            when {
                expression { return params.ETW_DEPLOY }
            }
            steps {
                deploySuccess("etw-docker-02", "ETW")
            }
        }

        stage('Deploy to INT') {
            when {
                expression { return params.INT_DEPLOY }
            }
            steps {
                deploySuccess("int-docker-01", "INT")

            }
        }

        stage('Deploy to PROD') {
            when {
                expression { return params.PROD_DEPLOY }
            }
            steps {
                deploySuccess("bn-docker-01", "PRD")
            }
        }

    }
}
def deploySuccess(String host, String stage) {
    sh "curl --header \"Content-Type: application/json\" \\\n" +
            "  --request POST \\\n" +
            "  ${env.DEPLOYMENT_MANAGER_URL}?stage="+ stage +"\\&host=" + host + "\\&port=${env.SERVICE_PORT}"
}