FROM openjdk:8-jdk-alpine
WORKDIR /opt/server-jdbc
ENV PORT 8888
EXPOSE $PORT
COPY target/*.jar /opt/server-jdbc/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/server-jdbc/app.jar