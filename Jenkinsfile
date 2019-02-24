node {
  stage ('SMC Checkout') {
            steps {
              git 'https://github.com/mirkomu/TestDivers'
            }
            stage ('Compile-Package') {
              sh 'mvn package'
            }     
}
      
