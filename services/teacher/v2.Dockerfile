FROM maven:3.8.6-eclipse-temurin-17 AS builder

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package

FROM openjdk:17

COPY --from=builder target/teacher-0.0.1-SNAPSHOT.jar teacher.jar

EXPOSE 8085

CMD ["java", "-jar", "teacher.jar"]