pipeline {
    agent {
        kubernetes {
        }
    }
    stages {
        stage('Build') {
            container('maven') {
                sh 'mvn -B -DskipTests clean package'
            }
//             steps {
//                 sh 'mvn -B -DskipTests clean package'
//             }
        }
    }
}