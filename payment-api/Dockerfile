FROM openjdk:8-jdk-alpine
FROM maven:alpine

# image layer
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /app
RUN mvn -v
RUN mvn clean install

ADD /target/*.jar payment-api.jar
ENTRYPOINT ["java","-jar","payment-api.jar"]
