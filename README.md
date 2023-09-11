
<img align="right" src="https://lh4.googleusercontent.com/-Ft6sZBfS1ojB7Lmqk8PGGpgLZDwaUC2MlRRvBVZbwQLpzOy6aJxrlnvxDfFZRzxgopUm5lRRhwoQ9jEpCzVrSxI77KrUhm-JCl1hFZWTyqqtV-tRN-N63Ng9RUn3mWN4Hz0mEv3=s0" width="200" height="200">

# Springboot boot Spring Security -JWT Authentication and Authorization-


## Introduction
This spring boot project aims to focus on the standards of security conditions by generating spring security features. The content of the project is to implement JWT authentication and authorization in a Spring Boot 3.0 application using Spring Security 6 and a Postgres database to store user credentials.
Moreover, The project is regarding  how easy it is to secure the application and protect the endpoints using JSON Web Tokens. In the roadmap of the project. Firstly adjusted the Postgres database and created a user table to store our credentials.
Secondly, configured Spring Security to use JWT and defined some security rules for our application. Finally, Tested the setup by building a simple API and using Postman to send authenticated requests.


## Technologies
- Java 17,
- Maven,
- Spring boot,
- OAuth(JWT),
- Docker,
- Dependencies:
    - Spring Web,
    - Spring Data JPA,
    - PostgreSQL Driver,
    - Lombok

## Architecture

<img  src="security\src\main\java\com\alibou\security\assets\img\architecture.png">


## Scripts
- Build the project:
    ```
    mvn clean install
    ```

- To start the application in the terminal:
    ```
    mvn spring-boot:run
    ```
- To dockerize the application:
  ```
  docker image ls
  ```


## Test
Completed API testing with __Postman and Swager__ each scenario was monitored. Tested CRUD API with endpoints.

## Deployment
The application was deployed on GitHub applying each step of sprints. There is only one branch which is __master__.
