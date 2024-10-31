#Base image for JDK
FROM openjdk:17-jdk-slim
LABEL org.opencontainers.image.authors="AndrijaLazic"
COPY target/admin_microservice-0.0.1-SNAPSHOT.jar admin_microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "admin_microservice-0.0.1-SNAPSHOT.jar"]
