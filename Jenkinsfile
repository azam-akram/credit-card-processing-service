pipeline{
    agent any
    stages {
        stage ("Git checkout") {
            steps {
                echo "Checking out code from GIT";
                git 'https://github.com/azam-akram/credit-card-processing-service.git'
            }
        }

        stage ("Building") {
            steps {
                echo "Building the source code";
                bat label: '', script: 'gradlew clean build'
            }
        }

        stage ("Unit testing") {
            steps {
                echo "Unit testing";
                bat label: '', script: 'gradlew test'
            }
        }

        stage ("Deploying") {
            steps {
                echo "Deploying project";
                //bat label: '', script: 'docker-compose up'
            }
        }
    }
    post {
        always {
            echo "Runs always"
        }
        success {
            echo "This runs only when Succesful"
        }
        failure {
            echo "This runs only when failed"
        }
        unstable {
            echo "This runs only when run is unstable"
        }
        changed {
            echo "This runs only when pipeline has been changed"
        }
    }
}