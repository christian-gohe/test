FROM maven:3-eclipse-temurin-17-alpine

COPY . /test
RUN cd /test && mvn package

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/test/target/test-0.0.1-SNAPSHOT.jar"]