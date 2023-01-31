String desc = '''プロジェクトをセットアップします。
空のプロジェクトリポジトリ、空のビルド用リポジトリを指定して、ジョブ定義ファイル、サブモジュール、初期Unityプロジェクトを加えてコミットします。
加えたプロジェクトをポーリングしてビルドするジョブを作成します。'''

pipelineJob('/initialize_project_submodule') {
  description(desc)
  parameters {
    stringParam('GITHUB_PROJECT_REPOSITORY')
    stringParam('GITHUB_BUILDER_REPOSITORY')
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
    CREATE_UNITY_PROJECT_JOB: '/unity/create_unity_project',
    SCM_SCRIPT_PATH: 'builder/jenkins/Jenkinsfile'
  )
  definition {
    cps {
      String src = new File("${JOBS_ROOT}/initialize_project_submodule.jenkinsfile").getText('UTF-8')
      script(src)
      sandbox()
    }
  }
}
