FROM alpine
# Install OpenJDK-11
RUN apk --no-cache add openjdk8 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
COPY target/youtube--crawler-0.0.1-SNAPSHOT.jar youtube--crawler-0.0.1.jar
ENTRYPOINT ["java","-jar","/youtube--crawler-0.0.1.jar"]
EXPOSE 8080:8080