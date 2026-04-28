# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built jar file
COPY --from=build /app/target/*.jar app.jar

# Set environment variables for production if needed
# ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]