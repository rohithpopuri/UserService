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
                git 'https://github.com/your-repo/springboot-app.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
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