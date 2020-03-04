FROM java:8
MAINTAINER "Paulson <paulson@gmail.com>"
VOLUME /tmp
COPY target/spring-security-0.0.1-SNAPSHOT.jar security1.jar
EXPOSE 8082
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/security1.jar"]