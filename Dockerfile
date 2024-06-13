# Define base image
FROM  openjdk:17-oracle
COPY target/winners-ledger.jar winners-ledger.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "winners-ledger.jar"]
