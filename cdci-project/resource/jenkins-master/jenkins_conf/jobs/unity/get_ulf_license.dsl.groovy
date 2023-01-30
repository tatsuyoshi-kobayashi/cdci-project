String desc = '''ulfライセンスを取得します。
初回はファイル入力待ちが入ります。'''

pipelineJob('/unity/get_ulf_license') {
  description(desc)
  parameters {
    stringParam('UNITY_VERSION')
    stringParam('UNITY_HOTFIX_VERSION')
  }
  properties {
    copyArtifactPermissionProperty {
      projectNames('*')
    }
  }
  environmentVariables(
    GET_UNITY_ALF_JOB: '/unity/get_alf_license'
  )
  logRotator {
    artifactNumToKeep(1)
  }
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/unity/get_ulf_license.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
