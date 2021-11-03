FROM quay.io/jkube/jkube-java-binary-s2i:0.0.9
ENV JAVA_APP_DIR=/deployments
LABEL org.label-schema.description="crawler for youtube" org.label-schema.version=0.0.1-SNAPSHOT org.label-schema.schema-version=1.0 org.label-schema.build-date=2021-10-21T21:11:03.448167644 org.label-schema.name=youtube-crawler
EXPOSE 8080 8778 9779
COPY /jkube-generated-layer-original/deployments /deployments/
