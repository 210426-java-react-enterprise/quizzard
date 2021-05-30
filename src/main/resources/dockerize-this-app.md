# Dockerizing a Servlet-based web application

### Prepare WAR file for containerization:
	mvn clean package

### Create Dockerfile in root of project directory
    touch Dockerfile

### Dockerfile contents:
	FROM tomcat:jdk8-openjdk
	EXPOSE 8080
	COPY target/*.war /usr/local/tomcat/webapps/quizzard.war
	CMD ["catalina.sh", "run"]

### Building Docker Image:
	sudo docker build -t quizzard .

### Running Container from Image:
	sudo docker run quizzard

### Getting IP address of Tomcat running inside of the container:
	sudo docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}'<CONTAINER_ID>

### Communicate with containerized application:
	curl http://<CONTAINER_IP_ADDRESS>:8080/quizzard/health
	