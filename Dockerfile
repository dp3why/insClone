# Use an official Maven image as the build environment
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download the project dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project source code
COPY src ./src

# Build the project
RUN mvn package -DskipTests

# Use a lightweight JDK base image for the application
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build environment
COPY --from=build /app/target/inst-0.0.1-SNAPSHOT.jar app.jar

# Expose the container port
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]


