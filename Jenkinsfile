pipeline {
    /*https://www.jenkins.io/doc/book/pipeline/jenkinsfile/*/
    agent any

    triggers {
        pollSCM('*/10 * * * *') //polling for changes, here once a minute
    }

    stages {
            stage('Clone') {
                steps {
                    checkout scm
                }
            }
            stage('Test and Build') {
                steps {
                    sh './gradlew clean build'
                }
            }
    }
}