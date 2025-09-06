def ciRepoUrl = 'https://github.com/radlak-shl/dsl-plugin-test.git'
def ciBranch  = 'main'

// Parse apps.json
def apps = new groovy.json.JsonSlurper().parseText(readFileFromWorkspace('ci/apps.json'))

apps.each { app ->
    pipelineJob(app.name) {
        definition {
            cpsScm {
                scm {
                    git {
                        remote { url(ciRepoUrl) }
                        branch(ciBranch)
                    }
                }
                scriptPath('Jenkinsfile.ecs')
                lightweight(true)
            }
        }
    }
}

