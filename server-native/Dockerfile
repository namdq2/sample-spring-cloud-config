FROM openjdk:8-jdk-alpine
WORKDIR /opt/server-git
ENV PORT 8888
EXPOSE $PORT
COPY target/*.jar /opt/server-git/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/server-git/app.jar