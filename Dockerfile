# Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
