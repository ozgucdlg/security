# Spring Security Project

A robust Spring Security implementation with JWT authentication, role-based access control, and rate limiting.

## Features

- 🔐 JWT-based Authentication
- 👥 Role-based Access Control
- ⚡ Rate Limiting with Burst Handling
- 🛡️ Comprehensive Security Configuration
- 📝 Global Exception Handling
- 🔄 Configurable Rate Limits
- 🧹 Automatic Resource Cleanup

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher

## Configuration

### Database Configuration
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jwtSecurity
spring.datasource.username=postgres
spring.datasource.password=password
```

### Rate Limiting Configuration
```properties
rate.limit.default=100
rate.limit.admin=1000
rate.limit.manager=500
rate.limit.burst.multiplier=2
```

### JWT Configuration
```properties
jwt.secret=${JWT_SECRET:your-256-bit-secret}
jwt.expiration=86400000
```

## Rate Limiting

The application implements role-based rate limiting with the following default limits:

- **Admin**: 1000 requests per minute
- **Manager**: 500 requests per minute
- **Default**: 100 requests per minute

Each role also has a burst multiplier (default: 2x) for handling temporary traffic spikes.

## Security Features

1. **JWT Authentication**
   - Stateless authentication
   - Configurable token expiration
   - Secure token validation

2. **Role-based Access Control**
   - Hierarchical role system
   - Fine-grained access control
   - Role-based rate limiting

3. **Rate Limiting**
   - Per-role rate limits
   - Burst handling
   - Automatic cleanup of unused buckets

4. **Exception Handling**
   - Global exception handler
   - Security-specific exceptions
   - Rate limit exceeded handling

## API Endpoints

### Authentication
- `POST /api/v1/auth/register` - Register new user
- `POST /api/v1/auth/authenticate` - Authenticate user
- `POST /api/v1/auth/refresh-token` - Refresh JWT token

### Protected Endpoints
- All other endpoints require valid JWT token
- Role-based access control applies

## Error Handling

The application provides consistent error responses for various scenarios:

```json
{
    "timestamp": "2024-03-14T12:00:00",
    "message": "Error message",
    "status": 400
}
```

## Getting Started

1. Clone the repository
2. Configure the database in `application.properties`
3. Set up your JWT secret in environment variables
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Security Considerations

1. **JWT Security**
   - Use strong secret keys
   - Keep token expiration times reasonable
   - Implement token refresh mechanism

2. **Rate Limiting**
   - Monitor rate limit thresholds
   - Adjust limits based on application needs
   - Consider implementing IP-based rate limiting

3. **Database Security**
   - Use strong passwords
   - Implement connection pooling
   - Regular security updates

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support, please open an issue in the GitHub repository.
