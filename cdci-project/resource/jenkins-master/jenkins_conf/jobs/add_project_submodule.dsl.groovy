String desc = '''ビルド用リポジトリのセットアップをします。
稼働中のプロジェクトリポジトリ、空のビルド用リポジトリを指定して、指定したブランチにビルド用リポジトリのサブモジュールを加えてコミットします。
加えたプロジェクトをポーリングしてビルドするジョブを作成します。'''

pipelineJob('/add_project_submodule') {
  description(desc)
  parameters {
    stringParam('GITHUB_PROJECT_REPOSITORY')
    stringParam('GITHUB_BUILDER_REPOSITORY')
    stringParam('PROJECT_REPOSITORY_BRANCH')
    stringParam('PROJECT_NAME')
    stringParam('UNITY_PROJECT_NAME')
    stringParam('UNITY_VERSION')
    stringParam('UNITY_HOTFIX_VERSION')
  }
  environmentVariables(
    GITHUB_CERTIFICATION_CHECK_JOB: '/github/certification_check',
    SETUP_UNITY_AGENT_JOB: '/jenkins/setup_unity_agent',
    PROJECT_SEED_JOB: '/jenkins/project_submodule_seed',
    COPY_PROJECT_TEMPLATE_JOB: '/aws/copy_project_template',
    GIT_USER_NAME: 'jenkins',
    GIT_USER_EMAIL: JENKINS_URL,
    SCM_SCRIPT_PATH: 'builder/jenkins/Jenkinsfile',
  )
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/add_project_submodule.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
