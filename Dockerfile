FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
LABEL authors="Rayka"

FROM tomcat:10.1.24-jdk21
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/ServiceOrderSystem.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080