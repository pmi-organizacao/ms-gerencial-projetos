FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
EXPOSE 8761

ARG EUREKA_SERVER=localhost
ARG RABBIT_MQ=localhost
ARG URL_DATABASE=gerencial-pmi-v3.cz4kuw26ocmq.us-east-1.rds.amazonaws.com
ARG PORT_DATABASE=5432
ARG USERNAME=postgres
ARG PASSWORD=admin0170

ENTRYPOINT java -jar -Dspring.profiles.active=dev app.jar