FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/tpfoyer-0.01-SNAPSHOT.jar tpfoyer.jar
ENTRYPOINT ["java", "-jar","/tpfoyer.jar"]