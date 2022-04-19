FROM openjdk:17-jdk-alpine
ADD /target/demo-0.0.1-SNAPSHOT.jar auth.jar
ENTRYPOINT ["java", "-jar", "auth.jar"]


#RUN ./mvnw

#FROM maven:3.6.3-openjdk-14 AS build
#COPY src /src
#COPY pom.xml /
#RUN mvn clean package -DskipTests
#
#FROM openjdk:14
#ARG JAR_FILE=target/*.jar
#COPY --from=build ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]



