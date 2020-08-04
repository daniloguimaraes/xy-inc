# xy-inc
XY Inc Point-of-Interest Service

### Technology Stack
The following items were used to develop this solution:

* [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), as main developing platform
* [Kotlin 1.3.72](https://kotlinlang.org/), as SDK and development language
* [Apache Maven 3](https://maven.apache.org/), as building and dependency management tool
* [Maven Wrapper](https://github.com/takari/maven-wrapper), allows everyone to run this application everywhere
* [Spring Boot 2.3.1](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/), to help startup
* [Jackson](https://github.com/FasterXML/jackson), as JSON marshaling/unmarshalling
* [HSQLDB (HyperSQL)](http://hsqldb.org/), as in-memory database
* [Swagger 2](https://swagger.io/), as REST API documentation tool
* [Junit 5](https://junit.org), as main testing tool


### How to run

Clone/download the repository, open a new terminal and type

Linux:
> ./mvnw spring-boot:run

Windows:
> mvnw.bat spring-boot:run


Note: running this command for the first time may take a few minutes.

A similar line as follows indicates that the startup was complete:

>  Started XyIncPoiServiceApplicationKt in 4.768 seconds (JVM running for 5.142)

----

### URL and Port

The base URL is:

> http://localhost:8080/

Opening this URL on your browser (or invoking HTTP GET on any HTTP client) should return a HTML file with the following `body`:

> PoI service it's up and running...

The default TCP port is `8080`.

To set-up a different port, edit  `src/main/resources/application.properties` file and add the following line:

> server.port = 8081

Then, restart the application and try again using the new port (ie 8081).

----

### Services

The complete list of available services and it's full-documentation can be found at:

> http://localhost:8080/docs


----

### Testing visually with Desmos

> Soon...