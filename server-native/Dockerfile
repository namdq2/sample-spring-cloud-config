FROM openjdk:8-jdk-alpine
WORKDIR /opt/server-native
ENV PORT 8888
EXPOSE $PORT
COPY target/*.jar /opt/server-native/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/server-native/app.jar