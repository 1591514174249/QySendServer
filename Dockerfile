FROM openjdk:8-jdk-alpine
MAINTAINER LXC <lf98zcxc@gmail.com>
COPY target/QySendServer.jar /app.jar
WORKDIR /
EXPOSE 10001
CMD ["java", "-jar", "/app.jar"]