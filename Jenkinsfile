node{
	stage('SCM Checkout') {
		git 'https://github.com/mirkomu/TestDivers'
	}
	stage('Compile-Package') {
	//	sh 'mvn -f /Backend/pom.xml -U clean package'
	}
	stage('Deploy war') {

	        sh 'service tomcat8 stop'
             //   sh 'mvn -f Backend/pom.xml -U clean package'
                sh 'cp /Backend/target/musicandbeerspairing-api/musicandbeerspairing-apiXXXXX.war /var/lib/tomcat8/webapps/'
                sh 'service tomcat8 start' 
	}
}
