pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        ARTIFACT_DIR = "/var/lib/jenkins/artifacts"
        APP_NAME = "app"
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Rotate Old Jar') {
            steps {
                script {
                    sh '''
                    set -

                    TODAY=$(date +"%b%d" | tr '[:upper:]' '[:lower:]')
                    cd ${ARTIFACT_DIR}

                    if [ -f ${APP_NAME}.jar ]; then
                        i=0
                        while true; do
                            if [ $i -eq 0 ]; then
                                NAME="${TODAY}${APP_NAME}.jar"
                            else
                                NAME="${TODAY}${APP_NAME}_$i.jar"
                            fi

                            if [ ! -f "$NAME" ]; then
                                mv ${APP_NAME}.jar "$NAME"
                                echo "Old jar renamed to $NAME"
                                break
                            fi
                            i=$((i+1))
                        done
                    else
                        echo "No previous jar found"
                    fi
                    '''
                }
            }
        }

        stage('Deploy New Jar') {
            steps {
                sh '''
                cp target/*SNAPSHOT.jar ${ARTIFACT_DIR}/${APP_NAME}.jar
                echo "New jar deployed as app.jar"
                '''
            }
        }

        stage('Archive in Jenkins') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
