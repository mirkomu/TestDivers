node {
	stage('SCM Checkout') {
		git 'https://github.com/mirkomu/TestDivers'
	}
	stage('install oracle jdbc'){
		mvn install:install-file -Dfile={path/to/your/ojdbc.jar} -DgroupId=com.oracle 
                -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
	}	
	stage('Compile-Package') {
		sh 'mvn -f Backend/pom.xml clean package'
	}
		
}




