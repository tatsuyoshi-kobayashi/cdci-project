String desc = '''指定されたUnityのエージェントが存在するかチェックします。
存在しない場合は、input承認後にAMIを作成します。'''

pipelineJob('/jenkins/setup_unity_agent') {
  description(desc)
  parameters {
    stringParam('UNITY_VERSION')
    stringParam('UNITY_HOTFIX_VERSION')
  }
  environmentVariables(
    LS_UNITY_AGENT_JOB: '/aws/ls_unity_agents',
    CONFIGURE_UNITY_AGENT_JOB: '/jenkins/configure_unity_agent',
    RUN_AGENT_BUILDER_JOB: '/aws/run_agent_builder',
    AGENT_BOOT_TIMEOUT: 3,
  )
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/jenkins/setup_unity_agent.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
