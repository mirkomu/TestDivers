node{
	stage('SCM Checkout') {
		checkout scm
	}
	stage('Compile-Package') {
		sh 'mvn -f Backend/pom.xml -U clean package'
	}
	stage('Deploy war') {
	     ////   sh 'sudo service tomcat8 stop'
             ////   sh 'mvn -f Backend/pom.xml -U clean package'
             //   sh 'cp Backend/target/musicandbeerspairing-api/musicandbeerspairing-apiXXXXX.war /var/lib/tomcat8/webapps/'
		  sh 'cp /var/lib/jenkins/workspace/testGithubMMU_master/Backend/target/musicandbeerspairing-api.war /var/lib/tomcat8/webapps/'
             ////   sh 'sudo service tomcat8 start' 
	}
}
