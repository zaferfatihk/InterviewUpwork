#Install Maven dependencies
. mvn clean install

> Run as a spring boot application.
>> Swagger: http://localhost:8080/swagger-ui/index.html#/
>> Open API: http://localhost:8080/api-docs


#Build a docker image

. go to project folder: ~/Programs/InterviewUpwork$
. run the command: docker-compose up --build
. run the app from the image: docker run -p8887:8888 app.jar:latest



