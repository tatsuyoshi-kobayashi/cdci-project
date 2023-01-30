# build-argからバージョン指定
ARG UNITY_IMAGE_VERSION
FROM unityci/editor:${UNITY_IMAGE_VERSION}

# jenkins用JAVAの環境変数
ENV JAVA_OPTS "-Duser.timezone=Asia/Tokyo -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"

# jre, sshサーバー のインストール
RUN apt-get update
RUN apt-get install -y openssh-server
RUN apt install -y default-jre
RUN apt-get clean \
 && rm -rf /var/lib/apt/lists/*

# ホストユーザーのuid gidからjenkins-agentユーザー作成
ARG HOST_UID
ARG HOST_GID
RUN groupadd -g ${HOST_GID} -o jenkins-agent && useradd -u ${HOST_UID} -g jenkins-agent jenkins-agent
RUN mkdir /home/jenkins-agent
RUN chown -R jenkins-agent:jenkins-agent /home/jenkins-agent && usermod -d /home/jenkins-agent jenkins-agent

# SSH公開鍵をボリュームマウントする
RUN mkdir /home/jenkins-agent/.ssh
VOLUME /home/jenkins-agent/.ssh
EXPOSE 22

# デフォルトワーキングディレクトリ
WORKDIR /home/jenkins-agent

# SSHを待ち受ける
CMD service ssh start -D
