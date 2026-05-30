Prompt: R — Role
Act as a Senior SDET Automation Architect with 15+ years of experience in enterprise-grade API automation frameworks using Java, Rest Assured, TestNG, Maven, Jackson/Gson, and CI/CD integration. Generate production-ready API automation code following industry best practices, scalable architecture, and maintainable framework design patterns.

I — Instructions
Generate a complete Rest Assured API Automation Framework using Java following enterprise-level standards.
Mandatory Requirements
	• Use Java + Rest Assured + TestNG + Maven only.
	• Follow enterprise-grade framework architecture.
	• Use TestNG annotations such as:
		○ @BeforeSuite
		○ @BeforeClass
		○ @BeforeMethod
		○ @Test
		○ @AfterMethod
		○ @AfterClass
		○ @AfterSuite
	• Use reusable utility classes and base classes.
	• Implement robust exception handling using structured try-catch blocks or explicit exception signatures.
	• Follow API automation best practices with modular reusable methods.
	• Generate clean and production-level code only.
	• Use RequestSpecification and ResponseSpecification.
	• Use reusable API client/service layer.
	• Use POJO classes for request and response payloads.
	• Use Jackson or Gson for serialization/deserialization.
	• Use assertions using TestNG Assert class.
	• Implement logging mechanism.
	• Add API response validations:
		○ status code
		○ response body
		○ response schema
		○ headers
		○ response time
	• Add negative and positive test scenarios.
	• Implement reusable utilities for:
		○ configuration reader
		○ test data handling
		○ JSON utility
		○ API utility
		○ authentication utility
	• Use properties/configuration files.
	• Use environment-based execution support.
	• Use DataProvider where applicable.
	• Maintain clean package structure.
	• Ensure framework scalability and readability.
Framework Standards
Generate framework using layered architecture:
	• base
	• clients
	• endpoints
	• models
	• tests
	• utils
	• constants
	• listeners
	• reports
Mandatory API Validations
	• Validate HTTP status codes.
	• Validate JSON response values.
	• Validate response headers.
	• Validate response schema.
	• Validate response time thresholds.
	• Validate authentication flows.
	• Validate error responses for invalid requests.
Authentication Support
Support:
	• Bearer Token
	• Basic Auth
	• OAuth2
	• API Key
Reporting
Integrate:
	• Extent Reports
	• Allure Reporting
CI/CD Ready
Framework should support:
	• Jenkins
	• GitHub Actions
	• Maven command execution
Coding Restrictions
	• Do not use bad coding practices.
	• Do not use hardcoded values.
	• Do not use duplicated logic.
	• Do not generate incomplete code.
	• Do not add explanations.
	• Do not add comments unless absolutely necessary.
	• Do not use Thread.sleep().
	• Use proper waits/timeouts wherever applicable.
Output Requirements
Provide only:
	1. Maven Project Structure
	2. Base Framework Classes
	3. API Client Classes
	4. Request/Response POJO Classes
	5. Utility Classes
	6. TestNG Test Classes
	7. Config Files
	8. pom.xml
	9. testng.xml
	10. Reporting Setup
	11. Sample Positive and Negative API Test Scripts
Output only runnable code.

C — Context
You are building a scalable enterprise API automation framework for testing REST APIs in microservices architecture.
The APIs include:
	• Authentication APIs
	• User APIs
	• CRUD APIs
	• Product APIs
	• Order APIs
Framework should support:
	• Multiple environments
	• Dynamic test data
	• Parallel execution
	• CI/CD integration
	• Reporting and logging
	• Token management
The framework should be reusable for enterprise applications with high maintainability and minimal code duplication.

E — Example
Example Request
POST /api/users
Payload:
{
"name": "Tejas",
"job": "QA Engineer"
}
Example Validation
	• Validate status code = 201
	• Validate response contains:
		○ id
		○ createdAt
		○ name
	• Validate response time < 3000ms
Example API Client Structure
public class UserClient {
public Response createUser(UserRequest requestBody) {
    return RestAssured
            .given()
            .spec(RequestSpecBuilderUtil.getRequestSpec())
            .body(requestBody)
            .when()
            .post(Endpoints.CREATE_USER)
            .then()
            .extract()
            .response();
}
}
Example Test Structure
@Test
public void verifyUserCreation() {
UserRequest request = new UserRequest("Tejas", "QA");
Response response = userClient.createUser(request);
Assert.assertEquals(response.getStatusCode(), 201);
Assert.assertNotNull(response.jsonPath().getString("id"));
}

P — Parameters
Technology Stack:
	• Java
	• Rest Assured
	• Maven
	• TestNG
	• Jackson/Gson
	• Extent Reports
	• Allure Reports
Framework Type:
	• Hybrid Enterprise API Automation Framework
Execution:
	• Cross-environment support
	• Parallel execution enabled
Architecture:
	• Modular
	• Scalable
	• Reusable
	• Maintainable
Code Quality:
	• Enterprise-grade
	• Minimal duplication
	• SOLID principles
	• Clean code standards

O — Output
Generate only:
	1. Complete Maven Project Structure
	2. pom.xml
	3. Base API Framework Classes
	4. RequestSpecification Builder
	5. ResponseSpecification Builder
	6. API Client Classes
	7. Endpoint Classes
	8. POJO Request/Response Models
	9. Utility Classes
	10. Config Reader
	11. Authentication Utilities
	12. TestNG Test Classes
	13. Positive and Negative API Tests
	14. Reporting Integration
	15. Logging Setup
	16. testng.xml
	17. Sample Execution Commands
No explanations.
No additional text.
Only runnable production-ready code.

T — Tone
Technical.
Enterprise-grade.
Production-ready.
Highly scalable.
Clean architecture.
Precise.
Minimalistic.
Automation architect level.
Zero bad coding practices.