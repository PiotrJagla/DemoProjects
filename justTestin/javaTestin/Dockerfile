FROM maven:3.8.5-openjdk-17 AS zbudowanie
COPY . .
RUN mvn clean install

FROM openjdk:17
COPY --from=zbudowanie /target/my-app-1.0-SNAPSHOT.jar myApp.jar

CMD ["java", "-jar", "myApp.jar"]
