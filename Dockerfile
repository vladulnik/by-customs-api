# Stage 1: Build the Spring Boot JAR
FROM gradle:8.2-jdk17 AS builder
WORKDIR /home/gradle/project

# Копируем весь код и собираем артефакт без тестов
COPY --chown=gradle:gradle . .
RUN gradle clean bootJar --no-daemon -x test

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
WORKDIR /app

# Копируем готовый JAR из первого этапа
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
