# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Java application (assuming it's in the target directory)
COPY target/InterviewUpwork.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]