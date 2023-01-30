# Jenkinsのイメージ
FROM jenkins/jenkins:2.375.2-lts

USER root

# HOSTマシンのuseridとgroupid
ARG HOST_UID
ARG HOST_GID

# jenkins用JAVAの環境変数　JCascで構成するのでインストールウィザードを実行しない
ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false -Duser.timezone=Asia/Tokyo -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"
ENV JENKINS_JAVA_OPTIONS="-Xms256m -Xmx1024m -Djava.awt.headless=true"

# jenkinsのデータをすべて格納するパスを指定
ENV JENKINS_HOME /var/jenkins_home

# jenkinsの構成データのパスを指定
ENV CASC_JENKINS_CONFIG /var/jenkins_conf

# ホストマシンと同様のユーザーを作成
RUN groupadd -g ${HOST_GID} -o host && usermod -u ${HOST_UID} -g host jenkins
RUN mkdir /home/jenkins
RUN mkdir /secret

# envsubst用のgettext-baseをインストール
RUN apt-get update && apt-get install -y gettext-base

# aws cliをインストール
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
RUN unzip awscliv2.zip && rm awscliv2.zip
RUN ./aws/install

# plugins.txtから必要jenkins pluginをインストール
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

RUN chown -R jenkins:host /home/jenkins && usermod -d /home/jenkins jenkins

USER jenkins
