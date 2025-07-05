# Use a Java 21 runtime image
FROM eclipse-temurin:21-jdk

# Add metadata
LABEL maintainer="nidheeshg@gmail.com" \
      application="spring-boot-app"

# ARG to allow overriding the jar pattern
ARG JAR_FILE=target/*.jar

# Copy the built jar into the image
COPY ${JAR_FILE} app.jar

# Expose the port your app runs on
EXPOSE 8080

# Launch the application
ENTRYPOINT ["java","-jar","/app.jar"]
