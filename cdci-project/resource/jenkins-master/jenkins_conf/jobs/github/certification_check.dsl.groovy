String desc = 'githubが認証済みかチェックします'

pipelineJob('/github/certification_check') {
  description(desc)
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/github/certification_check.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
