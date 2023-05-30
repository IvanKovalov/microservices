FROM maven:3.8.6-eclipse-temurin-17 AS builder

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package

FROM openjdk:17-buster

RUN apt-get update \
  && apt-get install -y --no-install-recommends mariadb-client \
  && rm -rf /var/lib/apt/lists/*

COPY --from=builder target/teacher-0.0.1-SNAPSHOT.jar teacher.jar

EXPOSE 8085

CMD ["java", "-jar", "teacher.jar"]