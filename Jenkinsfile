pipeline {
  agent any
  tools {
    maven "maven"
  }
  stages {
//     stage('version') {
//       steps {
//         withCredentials([usernamePassword(credentialsId: 'github-token-dmytrozuyenko', passwordVariable: 'github_token', usernameVariable: 'github_user')])        
//         {
//           sh 'git checkout dev'
//           script {
//             sh "mvn build-helper:parse-version versions:set \
//               -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVerson.nextIncrementalVersion}"
//               version.commit"
//             def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
//             def version = matcher[0][1]
//             env.IMAGE_NAME = "$version-$BUILD_NUMBER"
//           sh 'git add pom.xml'
//           sh 'git commit -m "[ci skip]"'
//           sh 'git push https://${github_token}@github.com/${github_user}/home.git --force'
//         }
    
//     stage('install') {
//       steps {        
//         sh "mvn clean install"
//       }
//     }
    
    stage('test') {
      steps {
        withCredentials([string(credentialsId: 'sonarqube-token', variable: 'sonarqube_token')]) {
          sh "mvn clean verify sonar:sonar -Dsonar.host.url=http://34.68.178.19:9000 -Dsonar.login=${sonarqube_token}"
//         sh "mvn sonar:sonar"
      }
    }
  }
}
