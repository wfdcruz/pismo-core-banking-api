# Pismo core banking api

To execute this application you can run by command line, executing the commands below

### Requirements

- JDK 8 or above
- Docker
- Docker compose

## Running locally by command line

Packing the application using maven wrapper:

> ./mvnw clean package

And run:

> ./mvnw spring-boot:run

## Running by docker

You can run using docker-compose.yml:
> docker-compose up -d

## Documentation

After application start you can access in browser the link: http://localhost:8080/swagger-ui/index.html#/

## Collections

To test application via http, you can import the collection and environment on postman.
> /collections/postman_collection.json

> /collections/postman_environment.json