# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ---- Run stage ----
FROM eclipse-temurin:21-jdk-ubi9-minimal
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Only for config-server: copy service account key

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]
