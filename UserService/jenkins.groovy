pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    environment {
        IMAGE_NAME = "springboot-app"
        CONTAINER_NAME = "springboot-container"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rohithpopuri/UserService.git'
            }
        }

        stage('Build Maven') {
            steps {
                dir('UserService')
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('UserService')
                sh 'docker build -t ${IMAGE_NAME} .'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker rm -f ${CONTAINER_NAME} || true'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker run -d -p 8081:8081 \
                --name ${CONTAINER_NAME} \
                ${IMAGE_NAME}
                '''
            }
        }
    }
}