# Credit Card Processing Service

This application demonstrates the basics of how to add new credit card information in the system. The service includes end-to-end system, i.e. taking credit card information from user and save it in the database. It also displays existing credit card information.

The backend of the system is developed in Java using Spring Boot and the front-end page is written in AngularJS.
The communication between backend and frontend happens through REST APIs.
### Technology used
- Spring Boot
- JPA
- H2 
- Docker
- AngularJS
- Freemarker
- Gradle
- Mockito

### Build the application

Use gradle wrapper to build this,
```
gradlew clean build
```

### Run application without docker container

- First, run the CreditCardApplication,
- In order to use credit card processing service application, go to 
```
http://localhost:9999/credit-card-processing-service/v1/
```
### Build and run docker images
The Dockerfile consists of all we need to create image of this application.
```Dockerfile
FROM java:8
MAINTAINER  Muhammad Azam Akram <akram.muhammadazam@gmail.com>
EXPOSE 8080
ADD build/libs/credit-card-processing-service-1.0-SNAPSHOT.jar /app/credit-card-processing-service.jar
WORKDIR /app/
CMD java -jar credit-card-processing-service.jar
```
Following is the docker-compose.yml
```yaml
 version: '2.2'
 
 services:
   credit-card-service:
     image: credit-card-service
     build:
       context: ./
       dockerfile: Dockerfile
     ports:
     - "9999:9999"
```
Now we are set to build the docker image and run credit card processing service inside the container,
```bash
    docker-compose up
```
### REST Controller
Call REST APIs,

Get All existing credit cards information
```
HTTP Header: 
Accept: application/json
GET: http://127.0.0.1:9999/credit-card-processing-service/v1/card
```

Add new credit card
```
HTTP Header: 
Accept: application/json
Content-Type: application/json
POST: http://127.0.0.1:9999/credit-card-processing-service/v1/card
body:
{
	"name":"Customer name",
	"cardNumber":"1234 8776 3567 0987",
	"accountLimit":100000
}
```

### More details

#### Database modeling
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="credit_card")
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "card_number", nullable=false)
    private String cardNumber;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "account_limit", nullable=false)
    private Integer accountLimit;
}
```
### Repository
```java
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Integer> {
}
```
### Spring boot data
It uses spring boot data properties to create and connect to the H2 database,
```yaml
application:
  api:
    version: v1

server:
  servlet:
    context-path: /credit-card-processing-service/${application.api.version}
  port: 9999

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password:
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: validate
  flyway:
    locations: classpath:db/migration
```
It also uses Flyway to create database tables and inserting some records,
```sql
CREATE TABLE credit_card (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  card_number VARCHAR(255) NOT NULL,
  balance INT(11) UNSIGNED,
  account_limit INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UNIQUE_KEY (card_number)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
```

```sql
INSERT INTO credit_card(id,name,card_number, balance,account_limit)
VALUES(1,'Customer 1','1111 1111 1111 1111',1000,100000);
INSERT INTO credit_card(id,name,card_number, balance,account_limit)
VALUES(2,'Customer 2','2222 2222 2222 2222',2000,200000);
INSERT INTO credit_card(id,name,card_number, balance,account_limit)
VALUES(3,'Customer 3','3333 3333 3333 3333',3000,300000);
INSERT INTO credit_card(id,name,card_number, balance,account_limit)
VALUES(4,'Customer 4',' 4444 4444 4444 4444',4000,400000);
INSERT INTO credit_card(id,name,card_number, balance,account_limit)
VALUES(5,'Customer 5','5555 5555 5555 5555',5000,500000);
```