FROM openjdk:8
RUN echo 'Dockerfile INIZIO'

ARG JAR_FILE=./target
ARG WORKDIR=/app

RUN echo $JAR_FILE 

RUN echo 'JAR_FILE=${JAR_FILE}' ${JAR_FILE}

COPY ${JAR_FILE}/*  app.jar

RUN echo 'we are running some # of cool things'



COPY  ${JAR_FILE}/spring-boot-data-jpa-0.0.1-SNAPSHOT.jar  ${WORKDIR}/spring-boot-data-jpa-0.0.1-SNAPSHOT.jar

#ADD ./target/spring-boot-data-jpa-0.0.1-SNAPSHOT.jar spring-boot-data-jpa-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","spring-boot-data-jpa-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","spring-boot-data-jpa-0.0.1-SNAPSHOT.jar"]
EXPOSE 8084



#docker build -t myJpa .

 
