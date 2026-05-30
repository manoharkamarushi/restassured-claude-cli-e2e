# REST Assured API Test Automation Framework

Enterprise-grade API automation framework using Java, REST Assured, TestNG, and Maven.

## Project Structure

```
src/
├── main/
│   ├── java/com/automation/
│   │   ├── base/              # Base classes (RequestSpec, ResponseSpec builders)
│   │   ├── clients/           # API client classes
│   │   ├── endpoints/         # API endpoint constants
│   │   ├── models/            # POJO models (request/response)
│   │   ├── utils/             # Utility classes
│   │   ├── constants/         # Constants and configurations
│   │   └── listeners/         # TestNG listeners
│   └── resources/
│       ├── config.properties  # Configuration file
│       ├── testng.xml        # TestNG configuration
│       └── log4j2.xml        # Logging configuration
└── test/
    └── java/com/automation/tests/  # Test classes
```

## Technology Stack

- **Java 11+**
- **REST Assured 5.3.2** - REST API testing
- **TestNG 7.8.1** - Testing framework
- **Maven 3.6+** - Build tool
- **Jackson 2.15.2** - JSON serialization
- **Gson 2.10.1** - JSON serialization (alternative)
- **Extent Reports 5.0.9** - Test reporting
- **Allure 2.23.0** - Test reporting
- **Log4j2 2.21.1** - Logging
- **SLF4J 2.0.9** - Logging facade

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Git

## Setup

1. **Clone Repository**
   ```bash
   git clone <repository-url>
   cd RestAssurredAPITestClaudeCLI
   ```

2. **Update Configuration**
   Edit `src/main/resources/config.properties`:
   ```properties
   base.uri=https://your-api-endpoint.com
   environment=qa
   api.key=your-api-key
   ```

3. **Install Dependencies**
   ```bash
   mvn clean install
   ```

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn test -Dtest=UserAPITest
```

### Run Tests in Parallel
```bash
mvn test -Dparallel.threads=3
```

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=src/main/resources/testng.xml
```

## Test Coverage

### Authentication Tests (AuthAPITest)
- User login validation
- User registration
- Token refresh
- Invalid credential handling

### User API Tests (UserAPITest)
- Get all users
- Get user by ID
- Create user
- Update user
- Delete user
- Search users

### Product API Tests (ProductAPITest)
- Get all products
- Get product by ID
- Create product
- Update product
- Delete product
- Search products

### Order API Tests (OrderAPITest)
- Get all orders
- Get order by ID
- Create order
- Update order
- Delete order
- Get order items

## Validations

All tests include comprehensive validations for:
- HTTP status codes
- Response content type
- Response time thresholds
- JSON response structure
- Required fields presence
- Field values
- Headers

## Reports

### Extent Reports
Generated at: `test-reports/ExtentReport_<timestamp>.html`

### Allure Reports
Generated at: `allure-results/`

View Allure report:
```bash
allure serve allure-results
```

## Logging

Logs are generated in:
- Console output
- `logs/test-execution.log` - Main log file
- `logs/test-execution-rolling.log` - Rolling log file

## Framework Features

✓ Layered architecture (Base, Clients, Models, Utils, Tests)
✓ Reusable utility classes
✓ RequestSpec and ResponseSpec builders
✓ Multiple authentication methods (Bearer, Basic, OAuth2, API Key)
✓ JSON serialization/deserialization (Jackson & Gson)
✓ Comprehensive API validations
✓ Extent and Allure reporting
✓ Parallel test execution
✓ Test listeners and retry mechanism
✓ Logging with Log4j2
✓ Environment-based configuration

## Best Practices

1. **Separation of Concerns** - Clear separation between clients, models, and tests
2. **Reusability** - Utility classes for common operations
3. **Configuration Management** - Externalized configuration
4. **Logging** - Comprehensive logging for debugging
5. **Assertions** - Strong assertions with meaningful messages
6. **Test Independence** - Tests don't depend on each other
7. **Data Management** - Use POJO models for type safety

## CI/CD Integration

### Jenkins
```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
}
```

### GitHub Actions
```yaml
- name: Run Tests
  run: mvn clean test
```

### GitLab CI
```yaml
test:
  script:
    - mvn clean test
```

## Troubleshooting

### Issue: Tests fail due to missing config.properties
**Solution**: Ensure `config.properties` exists in `src/main/resources/`

### Issue: API responses timeout
**Solution**: Increase timeout in `config.properties`:
```properties
request.timeout=10000
```

### Issue: Extent Reports not generating
**Solution**: Ensure `test-reports` directory exists and is writable

## Contributing

1. Follow the existing code structure
2. Add meaningful test descriptions
3. Use proper assertions and validations
4. Update README for new features
5. Ensure tests pass locally before pushing

## License

Proprietary - All Rights Reserved

## Support

For issues and questions, contact the QA team.
