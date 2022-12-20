FROM openjdk:17
WORKDIR /app
COPY build/libs/clevertec-test-task-1.0-SNAPSHOT.jar /clevertec-test-task-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/clevertec-test-task-1.0-SNAPSHOT.jar"]