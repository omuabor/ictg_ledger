# Define base image
FROM  openjdk:22-jdk-slim
LABEL authors="eviogheneomuabor"
ADD target/winners-ledger-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
