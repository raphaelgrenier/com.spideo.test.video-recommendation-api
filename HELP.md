# Read Me First

I used  [spring initializr](https://start.spring.io) with Java 16, SpringBoot 2.5.1, Spring Web dependencies and jar
packaging,

Don't forget to set $JAVA_HOME to project target Java 16 :
`export JAVA_HOME=/Users/raphaelgrenier/Library/Java/JavaVirtualMachines/openjdk-16.0.1/Contents/Home`

You can check if mvn configuration is fine with `mvn -v`

# Getting Started

Tests can be run :

- with the Maven command `mvn test` from the pom.xml root path
- with your favorite EDI by running tests from the project
  (on IntellijIDEA : 2nd click on project > `Run 'All Tests'`)

The project can be run with the maven command
`mvn spring-boot:run`

# Swagger documentation

To display swagger API documentation please go to  `{url}/swagger-ui/` or `{url}/swagger-ui/index.html`

Exemple for a local run : accessing http://localhost:8080 will redirect to http://localhost:8080/swagger-ui/

# Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#boot-features-developing-web-applications)

# Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

https://start.spring.io/