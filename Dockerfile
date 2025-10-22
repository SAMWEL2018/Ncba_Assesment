FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jre

# Environment variables
ENV APP_PORT=8094 \
    DB_HOST=localhost \
    DB_PORT=5432 \
    DB_NAME=ncba \
    SPRING_OUTPUT_ANSI_ENABLED=ALWAYS
WORKDIR /app
COPY --from=builder /build/target/NCBA_CASE_STUDY-1.0.1.jar app.jar
EXPOSE ${APP_PORT}
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--server.port=${APP_PORT}", "--spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}"]
