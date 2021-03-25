FROM openjdk:1.8-jre-slim AS base
EXPOSE 8696
LABEL stage=intermediate
WORKDIR /app
COPY --from=build /app/target/appgate-prueba-api.jar ./app.jar
#CMD ["java", "-Xmx200m", "-jar", "app.jar"]
RUN cd /app/appgate-prueba-api.jar
    mvn install 
RUN mvn package
