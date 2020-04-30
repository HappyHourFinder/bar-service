FROM adoptopenjdk/openjdk13:alpine-jre

LABEL maintainer="aime.mathieu1@gmail.com"

VOLUME /tmp

EXPOSE 8088

COPY target/bar-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
