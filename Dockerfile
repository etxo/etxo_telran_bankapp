FROM openjdk:17
MAINTAINER com.etxo
COPY target/bank_app-0.0.1-SNAPSHOT.jar etxo_bank_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/etxo_bank_app.jar"]

# docker build -t etxo/bank_app:latest .
# docker run -p 80:8080 etxo/bank_app:latest .
# docker push etxo/bank_app:latest
