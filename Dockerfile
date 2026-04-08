FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]