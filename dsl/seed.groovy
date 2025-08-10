import org.yaml.snakeyaml.Yaml

def ciRepoUrl = 'https://github.com/<your-org-or-user>/ci-ecs-pipelines.git'
def ciBranch  = 'main'

// Load the YAML file from the workspace
def appsFile = new File("${WORKSPACE}/ci/apps.yaml")
def apps = new Yaml().load(appsFile.text)

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

