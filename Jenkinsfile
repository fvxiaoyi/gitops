pipeline {
    agent {
        kubernetes {
            defaultContainer 'maven'
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