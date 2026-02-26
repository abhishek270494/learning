pipeline {
    agent any

    environment {
        IMAGE_NAME = "spring-app"
        CONTAINER_NAME = "spring-app"
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/abhishek270494/learning.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker stop $CONTAINER_NAME || true'
                sh 'docker rm $CONTAINER_NAME || true'
            }
        }

        stage('Run New Container') {
            steps {
                sh '''
                docker run -d \
                  --name $CONTAINER_NAME \
                  -p 8080:8080 \
                  $IMAGE_NAME
                '''
            }
        }
    }
}