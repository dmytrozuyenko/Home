pipeline {
  agent any
  tools {
    maven "maven"
  }
  environment {
    aws_account_id="209998915568"
    aws_default_region="us-east-2"
    image_repo_name="home-dev"
    image_tag="latest"
    repository_uri = "https://${aws_account_id}.dkr.ecr.${aws_default_region}.amazonaws.com/"
}
  stages {
//     stage('version') {
//       steps {
//         withCredentials([usernamePassword(credentialsId: 'github-token-dmytrozuyenko', passwordVariable: 'github_token', usernameVariable: 'github_user')]) {
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
    
//                 script {
//                     withCredentials([usernamePassword(credentialsId: 'github-token-dmytrozuyenko', passwordVariable: 'github_token', usernameVariable: 'github_user')]) {
//                         // git config here for the first time run
//                         sh 'git config --global user.email "jenkins@example.com"'
//                         sh 'git config --global user.name "jenkins"'

//                         sh "git remote set-url origin https://${github_user}:${github_token}@github.com/dmytrozuyenko/home.git"
//                         sh 'git add pom.xml'
//                         sh 'git commit -m "[ci skip]"'
//                         sh 'git push origin HEAD:jenkins-jobs'
//                     }
//                 }    
    // WORKS!
//     stage('install') {
//       steps {        
//         sh "mvn clean install"
//       }
//     }
    
    // WORKS!
//     stage('test') {
//       steps {
//         withCredentials([string(credentialsId: 'sonarqube-token', variable: 'sonarqube_token')]) {
//           sh "mvn clean verify sonar:sonar -Dsonar.host.url=http://34.68.178.19:9000 -Dsonar.login=${sonarqube_token}"
// //         sh "mvn sonar:sonar"
//         }
//       }
//     }
    
//     stage(‘Logging into AWS ECR’) {
//       steps {
// //         script {
// //           sh “aws ecr get-login-password — region ${AWS_DEFAULT_REGION} | docker login — username AWS — password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com”
// //         }

//       }
//     }

    
     stage('push') {
       steps {
//          sh "docker save homeacademy/home-application > home-application.tar"
//          sh "docker save homeacademy/data-migration > home-data-migration.tar"
         script {
           docker.withRegistry("${repository_uri}", "aws-ecr-auth") {
             docker.image("homeacademy/home-application").push()
           }
         }
       }
     }
    
  }
}
