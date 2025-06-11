# Stage 1: соберём JAR через Gradle Wrapper
FROM gradle:8.2-jdk17 AS builder
WORKDIR /home/gradle/project

# Копируем вместе с wrapper-скриптами
COPY --chown=gradle:gradle . .

# Собираем без тестов
RUN ./gradlew clean bootJar -x test

# Stage 2: запускаем в лёгком JRE
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
