node {
	stage('SCM Checkout') {
		git 'https://github.com/mirkomu/TestDivers'
	}
	stage('Compile-Package') {
		sh 'mvn -f Backend/pom.xml -B -DskipTests clean package'
	}
		
}




