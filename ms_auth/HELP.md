# AUTHENTICATION MICROSERVICE
* Port: 8081
* Path: /api/auth

### INTRODUCTION
This service is created for handling authentication and authorization needs. This service can register, unregister, log in a user, and also it can check user authority roles for authentication.

#### Auth service has 4 RESTFul endpoints, these endpoints:

* Register                 // PUBLIC
* Login                    // PUBLIC
* Unregister               // NEED ADMIN AUTHENTICATION
* check authentication     // Public, but it is for internal usage via FeignClient.

The main ability of this service is checking authority of every RESTful request on fleet application.

### Guides
#### You can use the postman collection for using endpoints:
https://github.com/DevelopmentHiring/CelalBerkayYelken2/blob/main/fleet.postman_collection.json

#### Also, you can check swagger documentation:
http://localhost:8081/api/auth/swagger

### TECHNICAL THINGS
* Service uses Spring Security for authentication.
* Service uses JWT approach for credential claims.
* Service uses PostgreSQL db via Spring data jpa.
* Service uses CQRS pattern for segregating the transactions.
* Service uses AOP for handling logs, exception and end-to-end fail scenario.
* Service uses Spring Web MVC for publishing RESTFul Endpoints.
* Service uses Lombok for decreasing lines on the model classes. (Lombok can serve Accessors, Mutators, Constructors, Builders, ToStrings, etc. on compile time, so you do not need to write down these things.)
* Service uses Springfox Swagger 3 for REST endpoints documentation.