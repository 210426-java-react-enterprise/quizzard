FROM tomcat:jdk8-openjdk
EXPOSE 8080
COPY target/*.war /usr/local/tomcat/webapps/quizzard.war
CMD ["catalina.sh", "run"]
