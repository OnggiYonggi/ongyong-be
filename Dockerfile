FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y ca-certificates
COPY build/libs/onggiyonggi-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]