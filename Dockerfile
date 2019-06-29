FROM java:8

EXPOSE 9002
ADD target/clientes-api-0.0.1-SNAPSHOT.jar clientes-api.jar
ENTRYPOINT ["java", "-jar", "clientes-api.jar"]