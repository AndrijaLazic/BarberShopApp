#Base image for JDK
FROM openjdk:17-jdk-slim
LABEL org.opencontainers.image.authors="AndrijaLazic"
COPY target/UserApp-0.0.1-SNAPSHOT.jar UserApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "UserApp-0.0.1-SNAPSHOT.jar"]
