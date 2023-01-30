String desc = 'alfライセンスを取得します。'

pipelineJob('/unity/get_alf_license') {
  description(desc)
  parameters {
    stringParam('UNITY_FULL_VERSION')
  }
  properties {
    copyArtifactPermissionProperty {
      projectNames('/unity/get_ulf_license')
    }
  }
  logRotator {
    artifactNumToKeep(1)
  }
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/unity/get_alf_license.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
