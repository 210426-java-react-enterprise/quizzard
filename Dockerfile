#The starting point of the image, the image we need to modify to make ours
FROM tomcat:8.5.66-jdk8-adoptopenjdk-hotspot
# container port to expose
EXPOSE 8080
#environment variables
ENV host_url="jdbc:postgresql://quizzard.c0nbqj7oncuf.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=quizzard"
ENV db_username=quizzard_app
ENV db_password=revature
# copy from local system into image
COPY target/quizzard.war webapps/quizzard.war
# The entrypoint for the container, remember containers only have 1 process
# ENTRYPOINT bin/startup.sh