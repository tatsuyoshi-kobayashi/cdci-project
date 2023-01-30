String desc = 'S3からプロジェクトテンプレートをダウンロードします'

pipelineJob('/aws/copy_project_template') {
  description(desc)
  environmentVariables(
    S3BUCKET_STACK: S3BUCKET_STACK,
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
      String src = new File("${JOBS_ROOT}/aws/copy_project_template.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
