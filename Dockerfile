FROM tomcat:9-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
ENV TZ=Asia/Taipei
