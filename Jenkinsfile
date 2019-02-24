pipeline {
	agent any
	stages {
		stage('Build') {
			steps {
				sh 'mvn -B -DskipTests clean package'
			}
		}
/*		stage('Test') {
			steps {
				//on a pas de test pour l'instant
				sh 'mvn test'
			}
		}
	}
  */post {
    always {
      junit "target/surefire-reports/*.xml"
    }
  }
}
