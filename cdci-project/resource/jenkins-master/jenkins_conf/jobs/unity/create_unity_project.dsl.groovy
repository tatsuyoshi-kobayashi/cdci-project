String desc = '''unityのプロジェクトを新規作成します。
EMPTY_PROJECTにチェックを入れている場合テンプレートを含みません。'''

pipelineJob('/unity/create_unity_project') {
  description(desc)
  parameters {
    stringParam('UNITY_VERSION')
    stringParam('UNITY_HOTFIX_VERSION')
    stringParam('UNITY_PROJECT_NAME')
    booleanParam('EMPTY_PROJECT', false)
  }
  properties {
    copyArtifactPermissionProperty {
      projectNames('/initialize_project')
    }
  }
  environmentVariables(
    GET_UNITY_ULF_JOB: '/unity/get_ulf_license',
    COPY_PROJECT_TEMPLATE_JOB: '/aws/copy_project_template'
  )
  logRotator {
    artifactNumToKeep(1)
  }
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/unity/create_unity_project.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
