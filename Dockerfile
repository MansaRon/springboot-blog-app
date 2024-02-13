FROM eclipse-temurin:17

LABEL mentainer="kramashia101@gmail.com"

WORKDIR /app

COPY target/springboot-blog-app-0.0.1-SNAPSHOT.jar /app/springboot-blog-app.jar

ENTRYPOINT ["java", "-jar", "springboot-blog-app.jar"]