FROM openjdk:8-jre
COPY target/demo-3-0.0.1-SNAPSHOT.jar calculator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx150m", "-jar", "calculator.jar"]