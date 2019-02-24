node {
  stage('SMC Checkout'){
    git 'https://github.com/mirkomu/TestDivers'
  }
  stage('Compile-Package') {
    def mvnHome = tool name: 'maven-3', type: 'maven'    
    sh "${mvnHome}/bin/mvn package"
  }     
}
      
