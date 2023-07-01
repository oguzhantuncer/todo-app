FROM openjdk:17
COPY target/todo-0.0.1-SNAPSHOT.jar app.jar
ENV SPRING_PROFILES_ACTIVE=development
CMD ["java", "-jar", "app.jar"]