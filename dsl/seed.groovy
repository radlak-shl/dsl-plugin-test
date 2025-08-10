import org.yaml.snakeyaml.Yaml

def ciRepoUrl = 'https://github.com/<your-org-or-user>/ci-ecs-pipelines.git'
def ciBranch  = 'main'

// Read apps list from CI repo (seed job must check this out)
def apps = new YamlSlurper().parseText(readFileFromWorkspace('ci/apps.yaml'))

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

