FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy Maven wrapper and dependency files first for better layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make Maven wrapper executable and download dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline -B && \
    rm -rf ~/.m2/repository/.cache

# Copy source code (this will be overridden by volume mount in docker-compose)
COPY src ./src

# Expose application port and LiveReload port
EXPOSE 4033 35729

# Default command (can be overridden in docker-compose)
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.fork=false", "-Dspring.devtools.restart.enabled=true", "-Dspring.devtools.livereload.enabled=true"]
