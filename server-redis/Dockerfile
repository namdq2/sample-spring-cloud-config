FROM openjdk:8-jdk-alpine
WORKDIR /opt/server-vault
ENV PORT 8888
EXPOSE $PORT
COPY target/*.jar /opt/server-vault/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/server-vault/app.jar