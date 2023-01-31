FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN echo 'Prima di copiare'
COPY ${JAR_FILE} app.jar
EXPOSE 8080
 
ENTRYPOINT ["java","-jar","/app.jar"]
 