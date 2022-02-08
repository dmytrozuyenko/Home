pipeline {
  agent any
  tools {
    nodejs "node"
  }
  stages {
    stage('install') {
      steps {
        sh "maven clean install"
      }
    }
  }
}
