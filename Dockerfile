FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/kn_order_managment_system_api-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/kn_order_managment_system_api-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /app/kn_order_managment_system_api-0.0.1-SNAPSHOT.jar