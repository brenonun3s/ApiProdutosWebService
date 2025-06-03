FROM ubuntu:latest AS build
LABEL authors="breno.santos"

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install quarkus:build

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build /target/produtos-runner.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]