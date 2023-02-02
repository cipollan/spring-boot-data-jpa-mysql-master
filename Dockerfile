From openjdk:8
#FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

From openjdk:8
copy ./target/*.jar employee-jdbc-0.0.1-SNAPSHOT.jar

ADD target/employee-jdbc-0.0.1-SNAPSHOT.jar  employee-jdbc-0.0.1-SNAPSHOT.jar
ADD target/*.jar   app.jar
CMD ["java","-jar","employee-jdbc-0.0.1-SNAPSHOT.jar"]
RUN logs -f 34 
RUN ls -ltr ${JAR_FILE}
RUN ls -ltr./target/*
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080


 
