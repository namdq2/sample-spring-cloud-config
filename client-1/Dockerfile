FROM openjdk:8-jdk-alpine
WORKDIR /opt/client-1
ENV PORT 8081
EXPOSE $PORT
COPY target/*.jar /opt/client-1/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/client-1/app.jar