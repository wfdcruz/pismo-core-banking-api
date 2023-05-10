FROM amazoncorretto:20.0.1-al2

WORKDIR /app
COPY ./target/core-banking-api-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=docker", "core-banking-api-0.0.1-SNAPSHOT.jar"]