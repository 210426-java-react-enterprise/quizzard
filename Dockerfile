#The starting point of the image, the image we need to modify to make ours
FROM tomcat:8.5.66-jdk8-adoptopenjdk-hotspot
# container port to expose
EXPOSE 8080

# arguments to be passed in through build command during build phase
# ARG db_host=fkjgh
# ARG db_user=sadkjfg
# ARG db_pass=sdlkjb
#
#
# #environment variables
# ENV host_url=${db_host}
# ENV db_username=${db_user}
# ENV db_password=${db_pass}

COPY setenv.sh bin/setenv.sh
# copy from local system into image
COPY target/quizzard.war webapps/quizzard.war
# The entrypoint for the container, remember containers only have 1 process
# ENTRYPOINT bin/startup.sh