# Base image
FROM openjdk:8-jdk-alpine

# Arguments to be provided when building the Docker image
ARG dbUrl=jdbc:h2:mem:test
ARG dbUsername=admin
ARG dbPassword=revature

# Environment variables that need to be made available to the container
ENV DB_URL=$dbUrl
ENV DB_USERNAME=$dbUsername
ENV DB_PASSWORD=$dbPassword
ENV JAR_FILE=./target/*.jar

# Establish a working directory for the container
WORKDIR /home/docker/data

# Move the compiled JAR file into the working directory with the name "app.jar"
COPY ${JAR_FILE} ./app.jar

# Allow the JAR file to be executed
RUN chmod +x ./app.jar

# Specify a port that the container should expose to interface with the application
EXPOSE 5000

# Starting command that Docker should execute when it runs a container based on
# the image created by this Dockerfile
ENTRYPOINT ["java", "-jar", "./app.jar"]