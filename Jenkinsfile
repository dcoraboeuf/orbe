pipeline {

    agent any

    stages {

        stage("Build") {
            steps {
                sh '''
                    ./gradlew \\
                        clean \\
                        versionFile \\
                        versionDisplay \\
                        build \\
                        --stacktrace \\
                        --console plain
                '''
            }
        }

    }

}