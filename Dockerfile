FROM openjdk:21-jdk-slim
RUN rm -f /app.jar
ARG JAR_FILE=app.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]