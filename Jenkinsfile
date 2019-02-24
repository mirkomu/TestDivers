pipeline {
	agent any
 stage('SMC Checkout'){
    git 'https://github.com/mirkomu/TestDivers'
  }
	stages {
		stage('Build') {
			steps {
				sh 'mvn -B -DskipTests clean package'
			}
		}
		stage('Test') {
			steps {
				sh 'mvn test'
			}
		}
	}
  post {
    always {
      junit "target/surefire-reports/*.xml"
    }
  }
}
