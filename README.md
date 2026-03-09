# Swagger

## Swagger (OpenAPI) – Complete Guide for Beginners

Swagger is a **toolset used to design, document, test, and explore REST APIs**. It helps developers understand and test APIs easily through a **web UI**.

In modern projects (especially **Spring Boot microservices**), Swagger is widely used for **API documentation and testing**.

---

# 1. What is Swagger?

**Swagger** is a framework used to **document REST APIs automatically**.

Today it follows the **OpenAPI Specification (OAS)**.

Simple meaning:

> Swagger converts your API code into **interactive documentation** where you can see all endpoints and test them directly.
> 

Example:

Instead of manually testing APIs in **Postman**, you can use **Swagger UI in the browser**.

Example UI:

```
GET /users
POST /users
DELETE /users/{id}
```

You can click and **execute APIs directly**.

---

# 2. Why Swagger is Used

Without Swagger:

- Developers must read documentation
- Use Postman manually
- Hard to understand API structure

With Swagger:

✅ Automatic API documentation

✅ Easy API testing

✅ Understand request & response structure

✅ Helps frontend and backend teams collaborate

✅ Shows all endpoints in one place

---

# 3. Main Components of Swagger

Swagger consists of **three main parts**.

### 1️⃣ OpenAPI Specification

This is the **standard format** describing the API.

Example:

```
/users:
  get:
    summary: Get all users
    responses:
      200:
        description: success
```

---

### 2️⃣ Swagger UI

A **web interface** where you can see and test APIs.

Example:

```
http://localhost:8080/swagger-ui/index.html
```

It shows:

- All endpoints
- Parameters
- Request body
- Response body

---

### 3️⃣ Swagger Editor

Used to **write OpenAPI definitions manually**.

Website:

```
https://editor.swagger.io
```

---

# 4. Swagger in Spring Boot (Most Important)

In **Spring Boot**, Swagger is implemented using:

### Library

```
springdoc-openapi
```

This automatically generates API docs.

---

# 5. Adding Swagger in Spring Boot

### Step 1: Add Dependency

If you are using **Spring Boot 3 + Java 21**, use this dependency.

**Maven**

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

---

### Step 2: Run Application

Run your Spring Boot app.

Open:

```html
http://localhost:8080/swagger-ui/index.html
```

Swagger UI will appear automatically.

---

# 6. Basic Example

### Controller

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<String> getUsers() {
        return List.of("Paras", "Rahul", "Amit");
    }

    @PostMapping
    public String createUser() {
        return "User Created";
    }
}
```

Swagger automatically generates documentation for this controller.

---

# 7. Swagger Annotations (Important)

Swagger allows you to add **annotations to improve documentation**.

---

## @Operation

Describes an API.

```java
@Operation(summary = "Get all users")
@GetMapping
public List<String> getUsers() {
    return List.of("Paras", "Rahul");
}
```

---

## @Parameter

Describes API parameters.

```java
@GetMapping("/{id}")
public String getUser(
        @Parameter(description = "User ID")
        @PathVariable int id) {
    return "User " + id;
}
```

---

## @ApiResponse

Describes responses.

```java
@ApiResponse(responseCode = "200", description = "User found")
@ApiResponse(responseCode = "404", description = "User not found")
```

Example:

```java
@Operation(summary = "Get user by ID")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
})
@GetMapping("/{id}")
public String getUser(@PathVariable int id) {
    return "User " + id;
}
```

---

# 8. Swagger Request Body Documentation

Example:

```java
@PostMapping
public User createUser(@RequestBody User user) {
    return user;
}
```

### Model

```java
public class User {

    private int id;
    private String name;
    private String email;
}
```

Swagger automatically generates:

```
{
  "id": 0,
  "name": "string",
  "email": "string"
}
```

---

# 9. Customizing API Information

You can define API info.

### Configuration Class

```java
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("QuizHub API")
                .version("1.0")
                .description("API documentation for QuizHub project"));
    }
}
```

Now Swagger UI will show:

```
QuizHub API
Version: 1.0
```

---

# 10. Swagger Security (JWT / Bearer Token)

For **secured APIs** you can add authentication.

Example:

```java
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .components(new Components()
            .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
}
```

Swagger UI will show **Authorize button**.

You can enter:

```
Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

# 11. Swagger Endpoints

Spring Boot automatically creates these endpoints.

| Endpoint | Purpose |
| --- | --- |
| `/swagger-ui.html` | Swagger UI |
| `/swagger-ui/index.html` | Swagger UI |
| `/v3/api-docs` | OpenAPI JSON |
| `/v3/api-docs.yaml` | OpenAPI YAML |

Example:

```
http://localhost:8080/v3/api-docs
```

Shows **complete API specification**.

---

# 12. How Swagger Works Internally

Process:

```
Controller
   ↓
Spring Boot scans annotations
   ↓
OpenAPI Specification generated
   ↓
Swagger UI reads specification
   ↓
Interactive API Documentation
```

---

# 13. Swagger in Microservices (Important for You)

Since you are building **microservices project**, you should:

### Add Swagger in each service

Example:

```
user-service
quiz-service
result-service
admin-service
```

Each service will have:

```
http://localhost:8081/swagger-ui
http://localhost:8082/swagger-ui
http://localhost:8083/swagger-ui
```

---

# 14. Advantages of Swagger

✅ Automatic documentation

✅ Interactive testing

✅ Reduces Postman usage

✅ Standard API structure

✅ Helps frontend developers

---

# 15. Limitations

❌ Not for business logic

❌ Can expose internal APIs if not secured

❌ Large projects need grouping

---

# 16. Best Practices

✔ Use **Swagger annotations**

✔ Document responses

✔ Use meaningful descriptions

✔ Secure production Swagger UI

✔ Group APIs

---

# 17. API Grouping (Advanced)

Example:

```java
@Bean
public GroupedOpenApi userApi() {
    return GroupedOpenApi.builder()
            .group("users")
            .pathsToMatch("/users/**")
            .build();
}
```

Swagger UI will show **separate sections**.

---

# 18. Swagger vs Postman

| Feature | Swagger | Postman |
| --- | --- | --- |
| Testing | Yes | Yes |
| Documentation | Automatic | Manual |
| API Visualization | Yes | Limited |
| Team Sharing | Good | Good |

Usually developers use **both**.

---

# 19. Real Example Workflow

Developer creates API

```
POST /users
GET /users
DELETE /users/{id}
```

Swagger automatically generates documentation.

Frontend developer opens:

```
http://localhost:8080/swagger-ui
```

They can:

- See request body
- Understand response
- Test APIs

---

# 20. Interview Questions (Very Important)

### What is Swagger?

Swagger is a toolset that uses **OpenAPI Specification to design, document, and test REST APIs**.

---

### What dependency is used in Spring Boot?

```
springdoc-openapi-starter-webmvc-ui
```

---

### What is Swagger UI?

A web interface used to **visualize and test REST APIs**.

---

### What is OpenAPI?

A specification used to **describe REST APIs in JSON or YAML format**.

---

# Project: User Management API with Swagger

### Technologies Used

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Swagger (springdoc-openapi)
- Maven

---

# 1. Create Project (Spring Initializr)

Search these dependencies in **Spring Initializr**:

```
Spring Web
Spring Data JPA
H2 Database
Lombok
Spring Boot DevTools
```

Then generate project.

Project structure:

```
swagger-demo
│
├── controller
│      UserController.java
│
├── service
│      UserService.java
│
├── repository
│      UserRepository.java
│
├── entity
│      User.java
│
├── config
│      SwaggerConfig.java
│
└── SwaggerDemoApplication.java
```

---

# 2. Add Swagger Dependency

Open **pom.xml**

Add this dependency.

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

This automatically enables **Swagger UI**.

---

# 3. Application Properties

`application.properties`

```
spring.application.name=swagger-demo

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
```

---

# 4. Entity Class

Create package **entity**

`User.java`

```java
package com.example.swaggerdemo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "User Entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "User Name", example = "Paras")
    private String name;

    @Schema(description = "User Email", example = "paras@gmail.com")
    private String email;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
```

---

# 5. Repository Layer

Create **repository package**

`UserRepository.java`

```java
package com.example.swaggerdemo.repository;

import com.example.swaggerdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
```

---

# 6. Service Layer

Create **service package**

`UserService.java`

```java
package com.example.swaggerdemo.service;

import com.example.swaggerdemo.entity.User;
import com.example.swaggerdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
```

---

# 7. Controller Layer (Swagger Annotations)

Create **controller package**

`UserController.java`

```java
package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.entity.User;
import com.example.swaggerdemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of users returned")
    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @Operation(summary = "Create new user")
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
```

---

# 8. Swagger Configuration

Create **config package**

`SwaggerConfig.java`

```java
package com.example.swaggerdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("User Management API")
                        .version("1.0")
                        .description("Spring Boot Swagger API Documentation"));
    }
}
```

---

# 9. Run Application

Run:

```
SwaggerDemoApplication
```

---

# 10. Open Swagger UI

Open browser:

```
http://localhost:8080/swagger-ui/index.html
```

You will see API documentation like:

```
GET /users
POST /users
GET /users/{id}
DELETE /users/{id}
```

You can test APIs directly.

---

# 11. Example API Test in Swagger

### Create User

POST `/users`

Request body:

```
{
 "name": "Paras",
 "email": "paras@gmail.com"
}
```

---

### Get Users

GET `/users`

Response:

```
[
 {
  "id":1,
  "name":"Paras",
  "email":"paras@gmail.com"
 }
]
```

---

# 12. Swagger Generated Documentation Endpoint

Swagger automatically generates OpenAPI spec.

```
http://localhost:8080/v3/api-docs
```

---

# 13. How Swagger Works Internally

Flow:

```
Controller
 ↓
Swagger scans annotations
 ↓
OpenAPI Specification generated
 ↓
Swagger UI reads JSON
 ↓
Interactive documentation
```

---

# 14. Final Project Structure

```
swagger-demo
│
├── config
│      SwaggerConfig.java
│
├── controller
│      UserController.java
│
├── entity
│      User.java
│
├── repository
│      UserRepository.java
│
├── service
│      UserService.java
│
├── SwaggerDemoApplication.java
│
└── application.properties
```

---

# 15. What You Learn from This Project

You understand:

✔ REST API development

✔ Swagger API documentation

✔ Swagger annotations

✔ OpenAPI specification

✔ API testing without Postman
