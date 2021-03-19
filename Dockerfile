FROM openjdk:1.8-jre-slim AS base
EXPOSE 8696
LABEL stage=intermediate

FROM maven:3.6.1-jdk-11-slim AS build
WORKDIR /app
COPY --from=build /app/target/ruleta-masiv-api.jar ./app.jar
#CMD ["java", "-Xmx200m", "-jar", "app.jar"]
RUN cd /app/ruleta-masiv-api.jar
    mvn install 
RUN mvn package
