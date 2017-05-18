
# TempR Backend

[![Spring](http://hortonworks.com/wp-content/uploads/2013/09/logo-spring-103x60.png)](https://spring.io/)

### Overview

TempR REST API

### Architecture

Application tech stack:
- Spring Boot

### How to run it

+ Build & Run w/ tests:
	* From the root folder run: ```mvn clean install```
	* From the 'tempr-backend-api' folder run: ```mvn spring-boot:run```
+ Build & Run w/o tests:
        * From the root folder run: ```mvn clean install -P prod```
        * From the 'tempr-backend-api' folder run: ```mvn spring-boot:run -P prod```

* Agnostic:  
    Run the compiled jar:
    ````
    java -jar <the-jar-name.jar>
    ````
