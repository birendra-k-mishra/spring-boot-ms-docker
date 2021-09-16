
FROM maven:3.6.0-jdk-11-slim AS build
workdir /home/app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/product-review-0.0.1-SNAPSHOT.jar /usr/local/lib/pr.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/pr.jar"]