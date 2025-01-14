FROM hub.c.163.com/tonight/oracle_jdk_1.8_131:131

MAINTAINER Gavin somta@qq.com

ADD console/target/*.jar juggle-server.jar

EXPOSE 8686

ENV JAVA_OPTS=" -Xss228k  -Xmx512m -Xms512m  -Duser.timezone=GMT+08 "

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /juggle-server.jar --spring.config.additional-location=/home/juggle/conf/application.properties" ]