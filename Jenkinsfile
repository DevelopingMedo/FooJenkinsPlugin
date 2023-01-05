pipeline {
    agent any

    stages {
        stage("Build Docker Images"){
            steps {
                sh 'echo "Location ${PWD}"'
                sh "pwd"
                script {
                    docker.build("buildtools:${env.BUILD_ID}",
                                 "-f Dockerfile.build .")
                    docker.build("applicationserver:${env.BUILD_ID}",
                                 "-f Dockerfile.deploy .")
                }
            }
        }
        stage("Build Process") {
            agent {
                docker {
                    image "buildtools:${env.BUILD_ID}"
                    args "-v $WORKSPACE:/workspace"
                }
            }
            stages {
               stage("compile") {
                   steps {
                       sh "mvn clean compile"
                   }
               }
               stage("test") {
                   steps {
                       sh "mvn test"
                   }
               }
               stage("build"){
                    steps {
                        sh "mvn clean install"
                    }
               }
            }
        }
        stage("Plugin Deployment") {
            input {
                message "Should we deploy the project?"

            }
            agent {
                docker {
                    image "applicationserver:${env.BUILD_ID}"
                    args "-d -v $WORKSPACE:/workspace"
                }
            }
            stages{
                stage("Check tooling on Deployment Container"){
                    steps {
                        sh "java --version"
                    }
                }
                stage('Run Docker container on remote hosts') {
                    steps {
                        sh "docker -H ssh://jenkins@justmedo.de run -d -p 25565:25565 /target/*.jar"
                    }
                }
            }
        }
    }
}