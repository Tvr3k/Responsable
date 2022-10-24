FROM openjdk:11
EXPOSE 8081
ADD target/responsable.jar responsable.jar
ENTRYPOINT ["java","-jar","/responsable.jar"]