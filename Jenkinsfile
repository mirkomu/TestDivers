node{
	stage('SCM Checkout') {
		git 'https://github.com/mirkomu/TestDivers'
	}
	stage('Compile-Package') {
	//	sh 'mvn -f /Backend/pom.xml -U clean package'
	}
	stage('Deploy war') {
	        sh 'sudo service tomcat8 stop' | echo 'adminig2019'
             //   sh 'mvn -f Backend/pom.xml -U clean package'
                sh 'sudo cp /Backend/target/musicandbeerspairing-api/musicandbeerspairing-apiXXXXX.war /var/lib/tomcat8/webapps/'|echo 'adminig2019'
                sh 'sudo service tomcat8 start'  | echo 'adminig2019'
	}
}
