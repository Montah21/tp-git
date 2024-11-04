FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/tpfoyer-1.0.jar -tpfoyer-1.0.jar
ENTRYPOINT ["java", "-jar","/tpfoyer-1.0.jar"]