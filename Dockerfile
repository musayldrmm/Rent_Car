FROM openjdk:11
WORKDIR /app
ADD target/rentcar-0.0.1-SNAPSHOT.jar  rentcar-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","rentcar-0.0.1-SNAPSHOT.jar"]