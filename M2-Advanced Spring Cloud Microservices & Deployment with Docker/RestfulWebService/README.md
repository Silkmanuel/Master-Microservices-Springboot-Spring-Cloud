## CREATION 01

```
FROM eclipse-temurin:17-jdk-jammy
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## Dockerfile - 2 - build jar file - Multi stage

```
# ===== STAGE 1: BUILD =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /home/app

## Copia apenas o pom.xml (cache de dependências)
COPY pom.xml .
#Copia o código fonte
COPY src ./src
#Compila o projeto
RUN mvn clean package -DskipTests
# ===== STAGE 2: RUNTIME =====
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Dockerfile - 3 - Caching

```
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

```
