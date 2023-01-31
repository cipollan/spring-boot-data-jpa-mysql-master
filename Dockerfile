FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN ls -ltr ${JAR_FILE}
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080