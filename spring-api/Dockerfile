FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copie o Maven Wrapper e o arquivo pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Conceda permissão de execução ao Maven Wrapper
RUN chmod +x ./mvnw

# Baixe todas as dependências sem compilação
RUN ./mvnw dependency:go-offline

# Copie o código-fonte do aplicativo
COPY src ./src

# Compile o aplicativo
#RUN ./mvnw package

# Defina o ponto de entrada para executar o Spring Boot
CMD ["./mvnw", "spring-boot:run"]
