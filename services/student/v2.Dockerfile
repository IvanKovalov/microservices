FROM maven:3.6.0-jdk-11 AS builder

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package

FROM openjdk:11

COPY --from=builder target/student-0.0.1-SNAPSHOT.jar student.jar

EXPOSE 8085

CMD ["java", "-jar", "student.jar"]