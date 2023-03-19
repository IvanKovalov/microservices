FROM maven:3.8.7-eclipse-temurin-19 AS builder

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package

FROM openjdk:19

COPY --from=builder target/classroom-0.0.1-SNAPSHOT.jar classroom.jar

EXPOSE 8085

CMD ["java", "-jar", "classroom.jar"]