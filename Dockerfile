# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/authorization-server-0.0.1-SNAPSHOT.jar authorization-server.jar

# Expose the application port
EXPOSE 8081

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "authorization-server.jar"]