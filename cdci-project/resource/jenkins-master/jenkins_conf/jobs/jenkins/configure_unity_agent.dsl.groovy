String desc = 'AWSに登録済みのCloudエージェントをec2-agent.yamlに追記してjcascを再読み込みします。'

pipelineJob('/jenkins/configure_unity_agent') {
  description(desc)
  environmentVariables(
    AWS_ACCOUNT_ID: AWS_ACCOUNT_ID,
    CASC_RELOAD_TOKEN: CASC_RELOAD_TOKEN,
    REGION: REGION,
    AGENT_KEYPAIR: AGENT_KEYPAIR,
    SG_ID: SG_ID,
    SUBNET_ID: SUBNET_ID,
    LS_UNITY_AGENT_JOB: '/aws/ls_unity_agents',
    CASC_TEMPLATE_CORE_SRC: '/var/jenkins_conf/template/ec2-agent-core.yaml.template',
    CASC_TEMPLATE_ADDITIVE_SRC: '/var/jenkins_conf/template/ec2-agent-additive.yaml.template',
    CASC_DEST: '/var/jenkins_conf/ec2-agent.yaml',
  )
  parameters {
    booleanParam('FORCE_RELOAD', false)
  }
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/jenkins/configure_unity_agent.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
