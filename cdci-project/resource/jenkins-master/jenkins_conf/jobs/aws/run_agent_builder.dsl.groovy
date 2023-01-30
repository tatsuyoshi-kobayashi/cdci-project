String desc = '''CloudFormation のスタック (agent-builder-runner.yaml)を実行し、指定UnityバージョンのAMIを作成します。
cloud-agent設定値を書き換え、cascをリロードします。'''

pipelineJob('/aws/run_agent_builder') {
  description(desc)
  parameters {
    stringParam('UNITY_VERSION')
    stringParam('UNITY_HOTFIX_VERSION')
    stringParam('UNITY_IMAGE_VERSION')
  }
  environmentVariables(
    STACK_ENVIRONMENT_NAME: STACK_ENVIRONMENT_NAME,
    S3BUCKET_STACK: S3BUCKET_STACK,
    REGION: REGION,
    IMAGE_BUILDER_ROLE: IMAGE_BUILDER_ROLE,
    BUILD_COMPONENT: BUILD_COMPONENT,
    INFRASTRUCTURE_CONFIGURATION: INFRASTRUCTURE_CONFIGURATION,
    CONFIGURE_UNITY_AGENT_JOB: '/jenkins/configure_unity_agent'
  )
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/aws/run_agent_builder.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
