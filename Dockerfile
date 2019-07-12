# we are extending everything from tomcat:8.0 image ...
FROM tomcat:8.0
MAINTAINER shounak
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
COPY target/hello-java-1.0.war /usr/local/tomcat/webapps/
