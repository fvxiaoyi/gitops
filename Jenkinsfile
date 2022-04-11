pipeline {
  agent {
    kubernetes {
      defaultContainer 'jnlp'
      yaml """
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            some-label: some-label-value
        spec:
          containers:
          - name: maven
            image: maven:3-openjdk-11-slim
            command:
              - cat
          - name: docker
            image: docker:20.10.14
            command:
              - cat
      """
    }
  }
  stages {
    stage('maven build') {
      steps {
        container('maven') {
          sh 'mvn -B -DskipTests clean package'
        }
      }
    }
    stage('Docker Build') {
        steps {
            container('docker') {
                sh "docker build -t dockerimage ."
            }
        }
    }
  }
}
// pipeline {
//     agent {
//         kubernetes {
//             defaultContainer 'maven'
//         }
//     }
//     stages {
//         stage('Run maven') {
//             steps {
//                 container('maven') {
//                      sh 'mvn -B -DskipTests clean package'
//                 }
//             }
//         }
//     }
// }