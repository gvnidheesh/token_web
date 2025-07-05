node {
  // Tools & vars
  def mvnHome
  def imageName        = 'nidheeshg/spring-boot'
  def dockerCredsId    = 'docker-hub-creds'
  currentBuild.result  = 'SUCCESS'

  try {
    stage('Preparation') {
      git 'https://github.com/gvnidheesh/token_web.git'
      mvnHome = tool 'M3'
    }

    stage('Build') {
      withEnv(["MVN_HOME=$mvnHome"]) {
        if (isUnix()) {
          sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
        } else {
          bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
      }
    }

    stage('Build Docker Image') {
      retry(2) {
        sh "docker build -t ${imageName}:latest ."
      }
    }

    stage('Docker Push') {
      withCredentials([usernamePassword(
        credentialsId: dockerCredsId,
        usernameVariable: 'DOCKER_USERNAME',
        passwordVariable: 'DOCKER_PASSWORD'
      )]) {
        retry(3) {
          // safe secret handling: single‑quoted Groovy → shell expands
          sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
          sh "docker push ${imageName}:latest"
        }
      }
    }

    stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archiveArtifacts 'target/*.jar'
    }

  } catch (err) {
    // mark failure so finally block can detect
    currentBuild.result = 'FAILURE'
    throw err

  } finally {
    if (currentBuild.result == 'SUCCESS') {
      echo "✅ Build #${env.BUILD_NUMBER} succeeded and image tagged ${baseImage}:${env.BUILD_NUMBER}"
    } else {
      echo "❌ Build #${env.BUILD_NUMBER} failed."
    }
  }
}
