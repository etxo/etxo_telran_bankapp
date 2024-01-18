FROM openjdk:17
MAINTAINER com.etxo
COPY target/bank_app-0.0.1-SNAPSHOT.jar etxo_bank_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/etxo_bank_app.jar"]

# docker image build -t etxo_bank:latest .
# docker run -p 8080:8080 etxo_bank
