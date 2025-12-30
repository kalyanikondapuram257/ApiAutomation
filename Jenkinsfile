pipeline {
    agent any
     triggers {
        cron('H/2 * * * *')   
    } 
parameters {
        choice(
            name: 'ENV',
            choices: ['PROD', 'DEV', 'QA', 'STAGE'],
            description: 'Select environment to run tests'
               )
    }
    
    tools {
        maven 'Maven-3.9.12'
        jdk 'JDK-21'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/kalyanikondapuram257/ApiAutomation.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn clean test -Denv=${params.ENV}"
            }
            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Archive Results') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar',
                                 allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'All tests passed!'
        }
        failure {
            echo 'Some tests failed!'
        }
    }
}
