FROM openjdk:alpine

VOLUME /tmp

COPY target/cms-eureka-server-0.0.1-SNAPSHOT.jar /app/cms-eureka-server.jar

EXPOSE 8761

ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/cms-eureka-server.jar"]
