String desc = '''AWSの自身がOwnerのEC2 AMIの中から UnityTarget:WebGLを探し、UnityVersionのリストを成果物に残します。
成果物は[/ami_list.txt]です。'''

pipelineJob('/aws/ls_unity_agents') {
  description(desc)
  environmentVariables(
    AWS_ACCOUNT_ID: AWS_ACCOUNT_ID,
  )
  properties {
    copyArtifactPermissionProperty {
      projectNames('*')
    }
  }
  logRotator {
    artifactNumToKeep(1)
  }
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/aws/ls_unity_agents.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
