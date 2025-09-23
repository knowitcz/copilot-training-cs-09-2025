# Code Style Guide

This document outlines the coding conventions and standards used in the AI Training Banking Application project.

## Table of Contents
- [General Principles](#general-principles)
- [Project Structure](#project-structure)
- [Java Coding Conventions](#java-coding-conventions)
- [Naming Conventions](#naming-conventions)
- [Documentation Standards](#documentation-standards)
- [Testing Patterns](#testing-patterns)
- [Spring Boot Conventions](#spring-boot-conventions)
- [Build and Dependency Management](#build-and-dependency-management)

## General Principles

### SOLID Principles
This project adheres to SOLID principles for maintainable and testable software:
- **S**ingle Responsibility Principle
- **O**pen/Closed Principle
- **L**iskov Substitution Principle
- **I**nterface Segregation Principle
- **D**ependency Inversion Principle

### Code Quality
- Write code that is readable, maintainable, and testable
- Prefer composition over inheritance
- Use dependency injection for loose coupling
- Validate input parameters using `Objects.requireNonNull()` where appropriate

## Project Structure

### Package Organization
The project follows a layered architecture with clear package separation:

```
cz.knowit.ai.bank/
├── api/                    # Business service layer
│   ├── rest/              # REST controllers
│   └── [service classes]  # Business logic services
├── database/              # Data access layer
│   ├── client/           # Client-related entities and services
│   └── product/          # Product-related entities and services
├── validator/            # Input validation utilities
├── configuration/        # Spring configuration classes
└── ApplicationRunner.java # Main application class
```

### Layer Responsibilities
- **API Layer**: Contains business logic and service implementations
- **Database Layer**: Handles data persistence and repository operations
- **Validator Layer**: Provides input validation functionality
- **Configuration Layer**: Contains Spring Boot configuration

## Java Coding Conventions

### Class Design
- Use `final` keyword for classes that should not be extended
- Use `final` keyword for fields that should not be reassigned
- Implement interfaces to define contracts
- Keep classes focused on a single responsibility

### Utility Classes
- Make utility classes `final` and provide a private constructor
- Throw `UnsupportedOperationException` in private constructor to prevent instantiation
- Use `static` methods for utility functionality

Example:
```java
public final class PhoneNumberValidator {
    
    private PhoneNumberValidator() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
    
    public static boolean isValid(String phoneNumber, Nationality nationality) {
        return phoneNumber != null && phoneNumber.matches(nationality.getPhoneNumberRegex());
    }
}
```

### Method Design
- Methods should be small and focused on a single task
- Use descriptive method names that clearly indicate their purpose
- Validate input parameters early in the method

### Exception Handling
- Use specific exception types rather than generic ones
- Create custom exceptions when domain-specific errors occur
- Include meaningful error messages

### Example:
```java
@Service
final class BranchBankService implements BankService {
    
    private final AccountService accountService;
    
    @Autowired
    public BranchBankService(AccountProdService accountService) {
        this.accountService = Objects.requireNonNull(accountService);
    }
    
    @Override
    public void withdrawAtBranch(long accountId, int amount) {
        accountService.withdrawMoney(accountId, amount);
    }
}
```

## Naming Conventions

### Classes
- Use **PascalCase** for class names
- Use descriptive names that clearly indicate the class purpose
- Add appropriate suffixes:
  - `Service` for service classes
  - `Controller` for REST controllers
  - `Repository` for data access objects
  - `Exception` for custom exceptions
  - `Test` for test classes

### Methods and Variables
- Use **camelCase** for method and variable names
- Use descriptive names that indicate purpose
- Boolean variables/methods should start with `is`, `has`, `can`, etc.

### Constants
- Use **UPPER_SNAKE_CASE** for constants
- Group related constants in enums or interfaces

### Examples:
```java
// Classes
public final class AccountProdService implements AccountService
class AccountController
enum Nationality

// Methods and Variables
public void withdrawMoney(long accountId, int amount)
private final AccountService accountService
boolean isValid

// Constants
private static final long serialVersionUID = 8219814305559421209L
```

## Documentation Standards

### JavaDoc Comments
- Use JavaDoc for all public classes, interfaces, and methods
- Include `@author` and `@since` tags for classes
- Document method parameters with `@param`
- Document return values with `@return`
- Document exceptions with `@throws`

### Class Documentation Template:
```java
/**
 * Service implementation for handling branch banking operations.
 * This service provides functionality for deposits, withdrawals, and transfers
 * that can only be performed at physical bank branches.
 *
 * @author [Author Name]
 * @since 1.0.0
 */
final class BranchBankService implements BankService {
    // implementation
}
```

### Method Documentation Template:
```java
/**
 * Validates if the provided phone number matches the format for the given nationality.
 *
 * @param phoneNumber the phone number to validate (can be null)
 * @param nationality the nationality to validate against (must not be null)
 * @return true if the phone number is valid for the given nationality, false otherwise
 * @throws NullPointerException if nationality is null and phoneNumber is not null
 */
public static boolean isValid(String phoneNumber, Nationality nationality) {
    // implementation
}
```

## Testing Patterns

### Test Class Organization
- Test classes should be in the same package structure as the classes they test
- Use the suffix `Test` for test class names
- Use descriptive test method names that explain what is being tested

### Test Method Naming
Use descriptive names that follow the pattern: `[what is being tested]_[expected outcome]`

### Example:
```java
final class PhoneNumberValidatorTest {
    
    @Test
    void validPhoneNumber() {
        assertThat(PhoneNumberValidator.isValid("+420123456789", Nationality.CZECH_REPUBLIC)).isTrue();
    }
    
    @Test
    void nullPhoneNumber() {
        assertThat(PhoneNumberValidator.isValid(null, Nationality.CZECH_REPUBLIC)).isFalse();
    }
    
    @Test
    void nullNationality() {
        assertThrows(NullPointerException.class,
                () -> PhoneNumberValidator.isValid("+420123456789", null));
    }
}
```

### Assertion Libraries
- Use **AssertJ** for fluent assertions (`assertThat`)
- Use **JUnit 5** assertions for exception testing (`assertThrows`)

## Spring Boot Conventions

### Dependency Injection
- Use constructor injection with `@Autowired`
- Validate injected dependencies with `Objects.requireNonNull()`
- Make injected fields `final`

### Annotations
- Use `@Service` for service layer components
- Use `@Repository` for data access layer components
- Use `@RestController` for REST API controllers
- Use `@Entity` for JPA entities
- Use package-private visibility for internal components when possible

### REST Controllers
- Use `@RequestMapping` at class level for base path
- Use specific HTTP method annotations (`@GetMapping`, `@PostMapping`)
- Use `@PathVariable` for URL parameters
- Include Swagger/OpenAPI annotations for API documentation

### Example:
```java
@RestController
@RequestMapping("/api/bank/v1")
class AccountController {
    
    private final AccountProdService accountService;
    
    @Autowired
    AccountController(AccountProdService accountService) {
        this.accountService = Objects.requireNonNull(accountService);
    }
    
    @Operation(summary = "Get account by ID")
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable(name = "id") long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
```

### JPA Entities
- Use `@Entity` and `@Table` annotations
- Use `@Id` and `@GeneratedValue` for primary keys
- Use `@Column` to specify database column mappings
- Use `@Enumerated(EnumType.STRING)` for enum fields
- Provide standard getters and setters
- Make entity classes `final` when possible

Example:
```java
@Entity
@Table(name = "T_CLIENT")
public final class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NATIONALITY")
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    
    // getters and setters
}
```

### Configuration Classes
- Use `@Configuration` annotation for configuration classes
- Include comprehensive JavaDoc documentation
- Keep configuration classes simple and focused

## Build and Dependency Management

### Maven Configuration
- Use Maven for dependency management and build automation
- Specify Java version in properties (`maven.compiler.source` and `maven.compiler.target`)
- Use specific version numbers for dependencies
- Group dependencies logically (Spring Boot, database, testing, etc.)

### Java Version
- Target Java 17 as specified in `pom.xml`
- Use modern Java features appropriately (records, var keyword, etc.)

### Dependencies
- Use Spring Boot starters for consistent dependency management
- Include testing dependencies with appropriate scope (`test`)
- Use H2 database for development and testing
- Include OpenAPI/Swagger for API documentation

### Application Configuration
- Use `application.properties` for application configuration
- Configure H2 database settings for development
- Enable H2 console for development (`spring.h2.console.enabled=true`)
- Use meaningful property names following Spring Boot conventions

## Code Formatting

### General Formatting
- Use 4 spaces for indentation (no tabs)
- Maximum line length of 120 characters
- Use blank lines to separate logical blocks of code
- Place opening braces on the same line as the statement

### Import Organization
- Group imports in the following order:
  1. Java standard library imports
  2. Third-party library imports
  3. Spring framework imports
  4. Project-specific imports
- Use single line imports (avoid `import.*`)
- Remove unused imports

### Example:
```java
package cz.knowit.ai.bank.api.rest;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.knowit.ai.bank.database.product.Account;
import cz.knowit.ai.bank.database.product.AccountProdService;
```

## Additional Guidelines

### Error Handling
- Use appropriate exception types
- Provide meaningful error messages
- Handle exceptions at the appropriate layer
- Use `UnsupportedOperationException` for methods that should not be called

### Security Considerations
- Validate all input parameters
- Use parameterized queries for database operations (JPA handles this automatically)
- Follow principle of least privilege

### Performance Considerations
- Use appropriate data types (prefer `int` over `Integer` for primitives)
- Avoid unnecessary object creation in loops
- Use efficient collection operations

---

This style guide should be followed consistently across the project to maintain code quality and readability. When in doubt, refer to existing code examples in the project as they demonstrate these conventions in practice.