#Base image for JDK
FROM openjdk:17-jdk-slim
LABEL org.opencontainers.image.authors="AndrijaLazic"
COPY target/ConfigServer-0.0.1-SNAPSHOT.jar ConfigServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ConfigServer-0.0.1-SNAPSHOT.jar"]