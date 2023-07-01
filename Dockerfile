FROM eclipse-temurin:17-jdk
COPY target/todo-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-Dspring.profiles.active=\"development\"", "-jar", "app.jar"]