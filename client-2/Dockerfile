FROM openjdk:8-jdk-alpine
WORKDIR /opt/client-2
ENV PORT 8082
EXPOSE $PORT
COPY target/*.jar /opt/client-2/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/client-2/app.jar