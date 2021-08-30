# TRIP API (Project Id:  UC #001)

## Description
To build APIs for Tourist portal that displays various tourist locations from different states

## Recommended Tech Stack
Java 1.8 & above

Spring/Spring Boot

Mongo/embedded DB (H2 - SQL)

Maven/Gradle

Junit/Mockito for Unit tests (services & controllers)

## Create & Setup Spring Boot project

1. Use Spring Initializr to creats the basic spring boot web application

2. Update below dependencies in pom.xml

```		
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>
		
		<dependency>
        	<groupId>javax.xml.bind</groupId>
        	<artifactId>jaxb-api</artifactId>
        	<version>2.3.0</version>
   		 </dependency>


		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
```

3. Create MongoDB Database

## API Methods

### Admin User

| Methods                         | Urls                         | Actions  |
| -------------                   |:-------------:               | -----:
| createTouristLocations          | /trip/api/touristLocation    | Creates the new Tourist Location |
| createAllTouristLocations       | /trip/api/touristLocationAll |   Creates Multiple Tourist Locations |
| updateTutorial       | /trip/api//touristLocation/{id} |   Updates the Tourist Locations |
| deleteTouristLocation       | /trip/api/touristLocation/{id} |   Delete the Tourist Locations |
| deleteAllTouristLocations       | /trip/api/touristLocation |   Deletes All Tourist Locations |

### Admin User + Normal User

| Methods                         | Urls                         | Actions  |
| nonCovidArea       | /trip/api/nonCovidArea |   retrieve all the non-covid affected tourist locations based on selected state sorted based on popularity index |
| getTouristLocationById       | /trip/api/touristLocation/{id} |   Gets the single Tourist Locations |
| getAllTouristLocation       | /trip/api/touristLocation |   Gets all the Tourist Locations |

## Login Functionality

You can signup and login using JWT token 







