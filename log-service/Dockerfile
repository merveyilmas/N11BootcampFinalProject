FROM openjdk:21-slim
EXPOSE 8082
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]