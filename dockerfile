FROM openjdk:18-jdk-alpine

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw -B package
FROM openjdk:18-jdk-alpine

COPY --from=build target/fast-maven-builds-1.0.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "fast-maven-builds-1.0.jar"]

