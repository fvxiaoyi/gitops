pipeline {
    agent any
    stages {
        stage('Maven Build') {
            agent {
                docker {
                    image 'maven:3.8.1-adoptopenjdk-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn -B -DskipTests clean package'
                script {
                    findFiles(glob: '**-service/Dockerfile').each{ file ->
                        def serviceDir = file.path.split('/')[0]
                        dir( serviceDir ) {
                            echo `ls`
                            sh 'java -Djarmode=layertools -jar target */*.jar extract --destination target/extracted'
                        }
                    }
                }
            }
        }
        stage('Docker Build') {
            agent {
                docker {
                    image 'docker:20.10.14'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                script {
                    def dockerRegistry = "https://registry.hub.docker.com/"
                    docker.withRegistry("${dockerRegistry}", "docker-login") {
                       findFiles(glob: '**-service/Dockerfile').each{ file ->
                           def serviceDir = file.path.split('/')[0]
                           dir( serviceDir ) {
                               def img = docker.build("ebinsu/${serviceDir}:${env.BUILD_NUMBER}", ".")
                               img.push()
                           }
                       }
                    }

                }
            }
        }
    }
}

// pipeline {
//   	agent {
//   		label "default"
//   	}
//   	stages {
//         stage('Maven Build') {
//             agent {
//                 label "maven"
//             }
//             steps {
//                 sh 'mvn -B -DskipTests clean package'
//             }
//         }
//     }
// }

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