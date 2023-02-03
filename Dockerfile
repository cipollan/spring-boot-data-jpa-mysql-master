From openjdk:8
#FROM eclipse-temurin:17-jdk-alpine
RUN echo 'Dockerfile INIZIO'

ARG JAR_FILE

RUN echo JAR_FILE=${JAR_FILE}' ${JAR_FILE}

COPY ${JAR_FILE} app.jar

RUN echo 'we are running some # of cool things'

copy ./target/spring-boot-data-jpa-0.0.1-SNAPSHOT.jar  spring-boot-data-jpa-0.0.1-SNAPSHOT.jar
ADD target/spring-boot-data-jpa-0.0.1-SNAPSHOT.jar spring-boot-data-jpa-0.0.1-SNAPSHOT.jar
ADD target/*.jar   app.jar
CMD ["java","-jar","spring-boot-data-jpa-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","/spring-boot-data-jpa-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080


 
