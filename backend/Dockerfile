### ====== BUILD STAGE ======
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# ESTE COMANDO ES CRÍTICO: CREA EL .JAR EJECUTABLE
RUN mvn -q -DskipTests package spring-boot:repackage


### ====== RUNTIME STAGE ======
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
