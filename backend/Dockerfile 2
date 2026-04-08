# ============================================
# STAGE 1: BUILD THE APPLICATION
# ============================================
# Use Maven with Java 17 to build the application
FROM eclipse-temurin:17-jdk-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project definition file
# This layer is cached unless pom.xml changes
COPY pom.xml .

# Copy the entire source code
COPY src ./src

# Install Maven (Alpine package manager)
RUN apk add --no-cache maven

# Build the application and create a JAR file
# -DskipTests: Skip running tests to speed up the build
# The JAR file will be created in /app/target/
RUN mvn clean package -DskipTests

# ============================================
# STAGE 2: CREATE THE RUNTIME IMAGE
# ============================================
# Use a smaller JRE image for running the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
# The JAR file name matches the artifactId and version in pom.xml
COPY --from=builder /app/target/bangla-learning-lms-1.0.0.jar app.jar

# Create a directory for the H2 database file
RUN mkdir -p /data

# Expose port 8080 (Spring Boot default port)
EXPOSE 8080

# Run the application
# -Djava.security.egd: Improves startup time in containerized environments
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
