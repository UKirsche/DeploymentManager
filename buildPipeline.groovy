//noinspection GroovyAssignabilityCheck
pipeline {

    agent any

    parameters {
        booleanParam(name: 'ETW_DEPLOY', defaultValue: false, description: 'Soll der Service auf ETW deployed werden?')
    }

    environment {
        DEPLOYMENT_MANAGER_PROJECT = "dmpfa"
        DEPLOYMENT_MANAGER = "http://etw-docker-03.bvaetw.de:8088/deployment-manager/api"
        DEPLOYMENT_MANAGER_URL = "${env.DEPLOYMENT_MANAGER}/projects/${env.DEPLOYMENT_MANAGER_PROJECT}/images"
        IMAGE = "etw-docker-03.bvaetw.de/dmp/dmp-fachanwendung"
        COMITTER_EMAIL = "Maganer"
        OWNVERSION = "1.1.0"
        COMMIT = "huhwdnwu"
        PIPELINE_NUMBER = sh(script: "curl ${env.DEPLOYMENT_MANAGER_URL}?version=${env.OWNVERSION}", returnStdout: true)
    }

    stages {
        stage('Push and deploy Image') {
            when {
                expression { return params.ETW_DEPLOY }
            }

            environment {
                TAG = sh(script: "curl --header \"Content-Type: application/json\" \\\n" +
                        "  --request POST \\\n" +
                        "  --data '{\n" +
                        "  \"user\": \"${env.USER}\",\n" +
                        "  \"image\": \"${env.IMAGE}\",\n" +
                        "  \"commit\": \"${env.COMMIT}\",\n" +
                        "  \"version\":  \"${env.OWNVERSION}\"\n" +
                        "}' \\\n" +
                        "  ${env.DEPLOYMENT_MANAGER_URL}",
                        returnStdout: true)
            }

            steps {

                echo "Docker build: ${env.TAG}"

                build job: 'deploy-manager',
                        parameters: [string(name: 'TAG', value: env.TAG),
                                     booleanParam(name: 'ETW_DEPLOY', value: true),
                                     booleanParam(name: 'INT_DEPLOY', value: false),
                                     booleanParam(name: 'PROD_DEPLOY', value: false)],
                        wait: false
            }
        }
    }
}
