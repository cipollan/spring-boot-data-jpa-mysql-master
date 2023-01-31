FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar

RUN echo 'mwnw clean package'
#RUN mvnw clean package
RUN ls -ltr ${JAR_FILE}

#RUN mwnw install
RUN echo 'Prima di copiare'
COPY ${JAR_FILE} app.jar
EXPOSE 8080
 
ENTRYPOINT ["java","-jar","/app.jar"]
 