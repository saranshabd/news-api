FROM openjdk:8-jdk
WORKDIR /app
COPY ./target/locals-digital-services.jar /app/
ENTRYPOINT ["java", "-jar", "/app/locals-digital-services.jar"]
