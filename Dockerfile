#Download oficial java runtime image
FROM openjdk:11

#Image maintainer
LABEL maintainer="Javier Roig Doménech"

# Add jar to container
COPY ./target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

#Executes the application
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]