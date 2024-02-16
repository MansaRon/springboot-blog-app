#FROM is to define the base image for docker
FROM eclipse-temurin:17

#LABEL is to define an author
LABEL mentainer="kramashia101@gmail.com"

#Directory on the container
WORKDIR /app

#Copy from target directory into the container
COPY target/springboot-blog-app-0.0.1-SNAPSHOT.jar /app/springboot-blog-app.jar

ENTRYPOINT ["java", "-jar", "springboot-blog-app.jar"]