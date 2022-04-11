pipeline {
    agent {
        kubernetes {
        }
    }
    stages {
        stage('Run maven') {
            steps {
                container('maven') {
                     sh 'mvn -B -DskipTests clean package'
                }
            }
        }
    }
}