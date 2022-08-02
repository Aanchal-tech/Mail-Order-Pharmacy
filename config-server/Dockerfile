FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "-Xms256m","-Xmx256m","/app.jar"]