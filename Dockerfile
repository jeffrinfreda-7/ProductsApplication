FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /opt

# Copy the JAR file from the local target directory to /opt in the container
COPY target/*.jar app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
