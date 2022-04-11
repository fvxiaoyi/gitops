pipeline {
  	agent {
  		label "default"
  	}
  	stages {
    		stage('Build') {
    			agent {
    				label "default"
    			}
    			steps {
    				sh 'ls -la'
    				sh 'mvn -version'
    				sh 'mvn clean compile'
    			}
    		}
    }
}

// pipeline {
//   agent {
//     kubernetes {
//       defaultContainer 'jnlp'
//       yaml """
//         apiVersion: v1
//         kind: Pod
//         metadata:
//           labels:
//             some-label: some-label-value
//         spec:
//           containers:
//           - name: maven
//             image: maven:3-openjdk-11-slim
//             volumeMounts:
//               - name: m2
//                 mountPath: /root/.m2
//             command:
//               - sleep
//             args:
//               - '99d'
//           - name: docker
//             image: docker:20.10.14
//             command:
//               - sleep
//             args:
//               - '99d'
//       """
//     }
//   }
//   stages {
//     stage('maven build') {
//       steps {
//         sh "echo `ls`"
//         container('maven') {
//           sh 'mvn -B -DskipTests clean package'
//         }
//         sh "echo `ls`"
//       }
//     }
//     stage('Docker Build') {
//         steps {
//             container('docker') {
//                 sh "docker build -t dockerimage ."
//             }
//         }
//     }
//   }
// }


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