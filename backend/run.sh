#!/bin/bash
# Start the Bangla Learning LMS backend

cd "$(dirname "$0")" || exit 1

echo "Starting Bangla Learning LMS Backend..."
echo "================================================"

# Run using the compiled JAR
if [ -f "target/bangla-learning-lms-1.0.0.jar" ]; then
    echo "Running from compiled JAR..."
    java -jar target/bangla-learning-lms-1.0.0.jar
else
    echo "JAR not found. Building project first..."
    mvn clean package -DskipTests
    java -jar target/bangla-learning-lms-1.0.0.jar
fi
