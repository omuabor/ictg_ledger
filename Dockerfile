# Define base image
FROM  openjdk:17-oracle
ADD target/winners-ledger.jar winners-ledger.jar
RUN apt-get update && apt-get install -y sudo
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/winners-ledger.jar"]
