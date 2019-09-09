pipeline {
   agent any

   environment {
     
     SERVICE_NAME = "calculator-test"
     REPOSITORY_TAG="${YOUR_DOCKERHUB_USERNAME}/${SERVICE_NAME}:v0.2.${BUILD_ID}"
   }
   
//   triggers {
//          pollSCM('* * * * *')

   stages {
      stage('Preparation') {
         steps {
            cleanWs()
            git credentialsId: 'GitHub', url: "https://github.com/${ORGANIZATION_NAME}/${SERVICE_NAME}"
         }
      }
      stage('Build') {
         steps {
            sh '''mvn clean package'''
         }
      }

      stage('Docker build Image') {
         steps {
           sh 'docker image build -t ${REPOSITORY_TAG} .'
         }
      }
      
      stage("Docker login") {
         steps {
           withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'docker-hub-credentials',
                             usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
           sh "docker login --username $USERNAME --password $PASSWORD"
           }
         }
      }
      
      stage("Docker push Image") {
         steps {
           sh "docker push ${REPOSITORY_TAG}"
         }
      }

      stage("Acceptance test") {
         steps {
           sleep 90
           sh "./mvnw test -Dcalculator.url=http://134.209.241.234:31002/"
           }
      }
   }
}