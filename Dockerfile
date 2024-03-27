FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/API_Music_Play-0.0.1-SNAPSHOT.jar API_Music_Play.jar

EXPOSE 7070
ENTRYPOINT ["java", "-jar", "API_Music_Play.jar"]