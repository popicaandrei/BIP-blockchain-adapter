FROM openjdk:17
ADD target/blockchain-adapter.jar blockchain-adapter.jar
ENTRYPOINT ["java", "-jar","blockchain-adapter.jar"]
EXPOSE 8081