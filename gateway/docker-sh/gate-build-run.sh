#! /bin/bash

REPOSITORIES='gate'
DATE=`date +%Y%m%d%H%M%S`

mv /home/biocome/gate/gate.jar /home/biocome/backups/gate-${DATE}.jar

rm -rf /home/biocome/gate/gate.jar
cp /home/jenkins/workspace/new_bio_platform/gateway/target/gateway.jar /home/biocome/gate/gate.jar

# Stop container, and delete the container.
CONTAINER_ID=`docker ps | grep ${REPOSITORIES} | awk '{print $1}'`
if [ -n "$CONTAINER_ID" ]; then
    docker stop $CONTAINER_ID
    docker rm $CONTAINER_ID
else #如果容器启动时失败了，就需要docker ps -a才能找到那个容器
    CONTAINER_ID=`docker ps -a | grep ${REPOSITORIES} | awk '{print $1}'`
    if [ -n "$CONTAINER_ID" ]; then  # 如果是第一次在这台机器上拉取运行容器，那么docker ps -a也是找不到这个容器的
        docker rm $CONTAINER_ID
    fi
fi
# Delete image early version.
IMAGE_ID=`sudo docker images | grep ${REPOSITORIES} | awk '{print $3}'`
if [ -n "${IMAGE_ID}" ];then
    docker rmi ${IMAGE_ID}
fi

rm -rf /home/biocome/gate/Dockerfile

cat >>/home/biocome/gate/Dockerfile<<EOF
FROM livingobjects/jre8
VOLUME /tmp
ADD gate.jar gate.jar
RUN bash -c 'touch /gate.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gate.jar"]
EOF

cd /home/biocome/gate
TAG=`date +%Y%m%d`
PRE='bio'
# Build
docker build -t ${PRE}/${REPOSITORIES}:${TAG} . &>/dev/null

# Run.
docker run -d --name ${REPOSITORIES} -p 8765:8765 ${PRE}/${REPOSITORIES}:${TAG}