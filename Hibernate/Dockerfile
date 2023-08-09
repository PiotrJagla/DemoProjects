FROM maven:3.8.5-openjdk-18 AS build
COPY . .
RUN mvn clean install package -DskipTests

FROM openjdk:18.0.1-jdk-slim

COPY --from=build /target/Hibernate-1.0-SNAPSHOT.jar Hibernate.jar
ENTRYPOINT ["java", "-cp", "Hibernate.jar", "org.example.Main"]