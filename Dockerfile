FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/helloworld-1.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]