
FROM eclipse-temurin:17-jdk-alpine as builder
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw clean package -DskipTests
FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
USER appuser
EXPOSE 8094

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]