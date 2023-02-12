
# Use an official MySQL image as the base image
FROM mysql:8.0

# Set environment variables
ENV MYSQL_ROOT_PASSWORD andrea
ENV MYSQL_DATABASE testdb
ENV MYSQL_USER andrea
ENV MYSQL_PASSWORD andrea

# Copy the schema file to the container
COPY schema.sql /docker-entrypoint-initdb.d/


EXPOSE 3306 33060
CMD ["mysqld"]


#docker build -t myjpa .
#docker build -t image_name .

#RUN docker-compose up
#docker build - < Dockerfile
