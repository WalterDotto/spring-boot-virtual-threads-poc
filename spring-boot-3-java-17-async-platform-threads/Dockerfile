
# For Java 17,
FROM openjdk:17-jdk
COPY target/spring-boot-3-java-17-async-platform-threads-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","--enable-preview", "-jar", "app.jar"]