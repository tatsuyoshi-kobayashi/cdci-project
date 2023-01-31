String templatePath = '/var/jenkins_conf/template/project_submodule_job.dsl.groovy.template'
String desc = "${templatePath}からプロジェクトのビルドジョブを生成します。"

pipelineJob('/jenkins/project_submodule_seed') {
  description(desc)
  authorization { permission('hudson.model.Item.Workspace:authenticated') }
  parameters {
    stringParam('GITHUB_REPOSITORY')
    stringParam('PROJECT_NAME')
    stringParam('UNITY_PROJECT_NAME')
    stringParam('UNITY_VERSION')
    stringParam('UNITY_HOTFIX_VERSION')
    stringParam('SCM_SCRIPT_PATH')
  }
  environmentVariables(
    PROJECT_JOB_DSL_TEMPLATE: templatePath,
    S3BUCKET_DEPLOY: S3BUCKET_DEPLOY,
  )
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/jenkins/project_submodule_seed.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
