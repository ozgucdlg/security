# Spring Boot Security with JWT Authentication and Authorization

<img align="right" src="https://lh4.googleusercontent.com/-Ft6sZBfS1ojB7Lmqk8PGGpgLZDwaUC2MlRRvBVZbwQLpzOy6aJxrlnvxDfFZRzxgopUm5lRRhwoQ9jEpCzVrSxI77KrUhm-JCl1hFZWTyqqtV-tRN-N63Ng9RUn3mWN4Hz0mEv3=s0" width="200" height="200">

## Introduction
This Spring Boot project implements a secure authentication and authorization system using JWT (JSON Web Tokens). The project demonstrates how to secure a Spring Boot 3.0 application using Spring Security 6 and PostgreSQL for user credential storage.

Key features:
- JWT-based authentication
- Stateless session management
- Secure password encoding
- Role-based authorization
- RESTful API endpoints
- PostgreSQL database integration

## Technologies
- Java 17
- Spring Boot 3.1.3
- Spring Security 6
- PostgreSQL
- Maven
- JWT (JSON Web Tokens)
- Docker
- Lombok

### Dependencies
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL Driver
- JWT Libraries (jjwt-api, jjwt-impl, jjwt-jackson)
- Lombok

## Architecture

<img src="https://github.com/ozgucdlg/security/blob/master/architecture.png">

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- PostgreSQL
- Docker (optional)

### Database Setup
1. Install PostgreSQL
2. Create a new database
3. Update `application.properties` with your database credentials

### Installation
1. Clone the repository:
```bash
git clone https://github.com/ozgucdlg/security.git
cd security
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

### Docker Deployment
1. Build the Docker image:
```bash
docker build -t security-app .
```

2. Run the container:
```bash
docker run -p 8080:8080 security-app
```

## API Endpoints

### Public Endpoints
- POST `/api/v1/auth/register` - Register a new user
- POST `/api/v1/auth/authenticate` - Authenticate and get JWT token

### Protected Endpoints
- GET `/api/v1/demo` - Demo endpoint (requires authentication)
- Other endpoints require valid JWT token

## Security Features
- JWT-based authentication
- Password encryption using BCrypt
- Stateless session management
- CSRF protection disabled for REST API
- Role-based access control

## Testing
The application has been tested using:
- Postman for API testing
- Swagger for API documentation
- Unit tests for security components

## Project Structure
```
src/main/java/com/alibou/security/
├── auth/           # Authentication related classes
├── config/         # Security configuration
├── repository/     # Data access layer
├── user/          # User related classes
└── SecurityApplication.java
```

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
For any questions or suggestions, please open an issue in the GitHub repository.
