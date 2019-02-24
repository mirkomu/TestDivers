node{
      agent any
      tools { 
        maven 'Maven 3' 
        jdk 'jdk8' 
    }
  stage('SMC Checkout'){
    git 'https://github.com/mirkomu/TestDivers'
  }
  stage('Compile-Package') {
    sh 'mvn package'
  }     
}
      
