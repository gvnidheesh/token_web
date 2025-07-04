pipeline {
    agent any

    environment {
        IMAGE_NAME = 'nidheeshg/springboot-app'
        DOCKER_CREDS = credentials('docker-hub-creds')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/gvnidheesh/token_web.git'
            }
        }

        stage('Build App') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t $IMAGE_NAME:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    sh '''
                    echo "$DOCKER_CREDS_PSW" | docker login -u "$DOCKER_CREDS_USR" --password-stdin
                    docker tag $IMAGE_NAME:${BUILD_NUMBER} $IMAGE_NAME:latest
                    docker push $IMAGE_NAME:${BUILD_NUMBER}
                    docker push $IMAGE_NAME:latest
                    '''
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}
