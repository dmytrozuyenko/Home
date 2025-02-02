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
//         withCredentials([usernamePassword(credentialsId: 'github-token', passwordVariable: 'github_token', usernameVariable: 'github_user')]) {
//           sh 'git checkout dev'
//           script {
//             sh "mvn build-helper:parse-version versions:set \
//               -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVerson.nextIncrementalVersion}"
//             def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
//             def version = matcher[0][1]
//             env.IMAGE_NAME = "$version-$BUILD_NUMBER"
//           }
//           sh 'git add pom.xml'
//           sh 'git commit -m "[ci skip]"'
//           sh 'git push https://${github_token}@github.com/${github_user}/home.git --force'
//         }
//       }
//     }  
          
    stage('install') {
      steps {        
        sh "mvn clean install"
      }
    }
    
    stage('test') {
      steps {
        withCredentials([string(credentialsId: 'sonarqube-token', variable: 'sonarqube_token')]) {
          sh "mvn clean verify sonar:sonar -Dsonar.host.url=http://34.68.178.19:9000 -Dsonar.login=${sonarqube_token}"
        }
      }
    }
    
    stage('push') {
      steps {
        withAWS(credentials: 'aws-auth-keys', region: 'us-east-2') {
          sh "aws ecr get-login-password --region ${aws_default_region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${aws_default_region}.amazonaws.com"
          sh "docker tag homeacademy/home-application:latest ${aws_account_id}.dkr.ecr.${aws_default_region}.amazonaws.com/home-application:latest"
          sh "docker push ${aws_account_id}.dkr.ecr.${aws_default_region}.amazonaws.com/home-application:latest"
          sh "docker tag homeacademy/data-migration:latest ${aws_account_id}.dkr.ecr.${aws_default_region}.amazonaws.com/data-migration:latest"
          sh "docker push ${aws_account_id}.dkr.ecr.${aws_default_region}.amazonaws.com/data-migration:latest"
        }
      }
    }

    stage('deploy') {
      steps {
        build job: 'home-dev_infra/main'
      }
    }
  }
}
