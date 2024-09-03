#FROM eclipse-temurin:17-jdk
# Use OpenJDK 11 with a slim base image
FROM openjdk:11-jre-slim
# Set the working directory inside the container
WORKDIR /optgir

# Copy the JAR file from the local target directory to /opt in the container
COPY target/*.jar app.jar

# Expose the port the application will run on
EXPOSE 8081

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
