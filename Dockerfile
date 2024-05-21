# Etapa de construcción
FROM openjdk:17-alpine AS builder
WORKDIR /workspace

# Copiar el archivo JAR generado por Maven/Gradle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rentcars-0.0.1-SNAPSHOT.jar

# Extraer las capas del JAR usando layertools
RUN java -Djarmode=layertools -jar rentcars-0.0.1-SNAPSHOT.jar extract

# Etapa final
FROM openjdk:17-alpine
WORKDIR /workspace

# Copiar las capas extraídas en la etapa de construcción
COPY --from=builder /workspace/dependencies/ ./
COPY --from=builder /workspace/spring-boot-loader/ ./
COPY --from=builder /workspace/snapshot-dependencies/ ./
COPY --from=builder /workspace/application/ ./

# Configurar el ENTRYPOINT para la aplicación
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]