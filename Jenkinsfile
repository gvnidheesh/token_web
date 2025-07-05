node {
    def mvnHome
    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://github.com/gvnidheesh/token_web.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }
	//environment {
    //    REGISTRY_URL = 'hub.ehealth.kerala.gov.in'
   //     IMAGE_NAME = "${REGISTRY_URL}/ehealth/token"
   //     DOCKER_CREDENTIALS_ID = 'ehealth-docker-creds'
   // }
    environment {
        IMAGE_NAME = 'nidheeshg/spring-boot'
        DOCKER_CREDENTIALS_ID = 'docker-hub-creds'
    }
      def imageName = 'nidheeshg/spring-boot'
    stage('Build') {
        // Run the maven build
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
		    credentialsId: 'docker-hub-creds',
		    usernameVariable: 'DOCKER_USERNAME',
		    passwordVariable: 'DOCKER_PASSWORD'
		  )]) {
		    // use a single‑quoted shell string so Groovy doesn't interpolate the secret
		    retry(3) { 
			    sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
			    // imageName is safe to interpolate here
			    sh "docker push ${imageName}:latest"
		    }
		  }
}

    stage('Results') {
    
        junit '**/target/surefire-reports/TEST-*.xml'
        archiveArtifacts 'target/*.jar'
    }   
    
    post {
			  success {
			    echo '✅ All done!'
			  }
			  failure {
			    echo '❌ Still failing after retries.'
			  }
		}     
}
