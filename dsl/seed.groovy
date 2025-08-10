def ciRepoUrl = 'https://github.com/<your-org-or-user>/ci-ecs-pipelines.git'
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

