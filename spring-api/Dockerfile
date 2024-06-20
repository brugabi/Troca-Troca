FROM eclipse-temurin:21-jdk-jammy
COPY . .

# Conceda permissão de execução ao Maven Wrapper
RUN chmod +x ./mvnw

RUN ./mvnw clean install -DskipTests

# Defina o ponto de entrada para executar o Spring Boot
CMD ["java", "-jar", "./target/spring-api-0.0.1-SNAPSHOT.jar"]
