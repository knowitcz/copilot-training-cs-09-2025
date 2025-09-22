# Code Style Guide

This document describes the code conventions and standards used in the AI Training Banking Application project.

## Table of Contents

1. [General Principles](#general-principles)
2. [Java Language Standards](#java-language-standards)
3. [Package Structure](#package-structure)
4. [Class Design](#class-design)
5. [Method Design](#method-design)
6. [Naming Conventions](#naming-conventions)
7. [Spring Framework Conventions](#spring-framework-conventions)
8. [JPA/Hibernate Conventions](#jpahibernate-conventions)
9. [REST API Conventions](#rest-api-conventions)
10. [Exception Handling](#exception-handling)
11. [Testing Standards](#testing-standards)
12. [Documentation](#documentation)

## General Principles

- **Immutability First**: Prefer immutable objects and final fields where possible
- **Null Safety**: Use `Objects.requireNonNull()` for constructor parameters
- **Clear Intent**: Code should be self-documenting with clear method and variable names
- **Single Responsibility**: Each class and method should have a single, well-defined purpose
- **SOLID Principles**: Follow SOLID design principles throughout the codebase

## Java Language Standards

### Language Version
- **Java 17** is the target language version
- Use modern Java features where appropriate (records, switch expressions, etc.)

### Code Formatting
- **Indentation**: 4 spaces (no tabs)
- **Line Length**: Maximum 120 characters per line
- **Braces**: K&R style (opening brace on same line)
- **Blank Lines**: One blank line between methods, two between classes

### Import Organization
- Group imports in the following order:
  1. Standard Java packages (`java.*`, `javax.*`, `jakarta.*`)
  2. Third-party libraries
  3. Project packages (`cz.knowit.ai.bank.*`)
- Use wildcard imports sparingly (avoid `import package.*`)
- Remove unused imports

## Package Structure

The project follows a layered architecture with clear separation of concerns:

```
cz.knowit.ai.bank
├── api/                    # Business logic layer
│   ├── rest/              # REST controllers
├── configuration/          # Spring configuration classes
├── database/              # Data access layer
│   ├── client/            # Client-related entities and services
│   ├── product/           # Account-related entities and services
│   └── transaction/       # Transaction-related entities and services
└── validator/             # Validation utilities
```

## Class Design

### Access Modifiers
- **Classes**: Use `final` for classes that shouldn't be extended
- **Package-private**: Default access level for implementation classes
- **Public**: Only for API interfaces and main application classes

### Class Structure Order
1. Static constants
2. Instance fields (private final preferred)
3. Constructors
4. Public methods
5. Package-private methods
6. Private methods
7. Static methods
8. Inner classes

### Example Class Structure
```java
@Service
final class BranchBankService implements BankService {
    
    private final AccountService accountService;
    
    @Autowired
    public BranchBankService(AccountProdService accountService) {
        this.accountService = Objects.requireNonNull(accountService);
    }
    
    @Override
    public void makeTransferAtBranch(long fromAccountId, long toAccountId, int amount) {
        accountService.transferMoney(fromAccountId, toAccountId, amount);
    }
}
```

## Method Design

### Method Signatures
- Use meaningful parameter names
- Prefer primitive types for simple parameters (`long id`, `int amount`)
- Use `Objects.requireNonNull()` for non-null validation

### Method Naming
- Use verb phrases for methods that perform actions (`transferMoney`, `withdrawMoney`)
- Use noun phrases for getters (`getAccountById`, `getBalance`)
- Use `is/has` prefixes for boolean methods (`isValid`, `hasBalance`)

## Naming Conventions

### Classes and Interfaces
- **PascalCase** for class names
- **Descriptive names** that indicate purpose
- **Interface naming**: No `I` prefix (e.g., `BankService` not `IBankService`)
- **Implementation naming**: Suffix with purpose (e.g., `AccountProdService`, `BranchBankService`)

### Methods and Variables
- **camelCase** for method and variable names
- **Meaningful names** that describe purpose
- **No abbreviations** unless they are well-known (e.g., `id`, `url`)

### Constants
- **UPPER_SNAKE_CASE** for static final constants
- **Descriptive names** with units where applicable

```java
private static final int ATM_LIMIT = 10000;
private static final long serialVersionUID = 8219814305559421209L;
```

### Packages
- **lowercase** with dots as separators
- **Meaningful hierarchy** reflecting application structure
- **No underscores or mixed case**

## Spring Framework Conventions

### Component Annotations
- `@Service` for business logic components
- `@Repository` for data access components
- `@RestController` for REST endpoints
- `@Configuration` for configuration classes

### Dependency Injection
- **Constructor injection** is preferred over field injection
- Use `@Autowired` on constructors
- Validate constructor parameters with `Objects.requireNonNull()`

```java
@Autowired
public BranchBankService(AccountProdService accountService) {
    this.accountService = Objects.requireNonNull(accountService);
}
```

### Bean Naming
- Use default Spring bean naming (first letter lowercase)
- Explicitly name beans only when necessary

## JPA/Hibernate Conventions

### Entity Classes
- Use `@Entity` annotation
- Specify `@Table(name = "TABLE_NAME")` with explicit table names
- Use `final` classes where inheritance is not needed

### Field Mapping
- Use `@Column(name = "COLUMN_NAME")` for explicit column naming
- Use `@Id` and `@GeneratedValue` for primary keys
- Prefer `GenerationType.IDENTITY` for auto-generated IDs

### Relationships
- Use appropriate relationship annotations (`@ManyToOne`, `@OneToMany`, etc.)
- Use `@JoinColumn` to specify foreign key columns

```java
@Entity
@Table(name = "T_ACCOUNT")
public final class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
}
```

### Repository Interfaces
- Extend `JpaRepository<Entity, ID>`
- Use `@Repository` annotation
- Package-private access level for implementation details
- Use `@Query` for custom queries with JPQL

```java
@Repository
interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = (a.balance - ?2) WHERE a.id = ?1")
    void withdrawMoney(Long accountId, int amount);
}
```

## REST API Conventions

### Controller Classes
- Use `@RestController` annotation
- Package-private access level
- Use `@RequestMapping` for base path
- Constructor injection for dependencies

### Endpoint Mapping
- Use appropriate HTTP methods (`@GetMapping`, `@PostMapping`, etc.)
- Follow RESTful URL patterns: `/api/bank/v1/resource/{id}`
- Use `@PathVariable` for URL parameters
- Use descriptive parameter names

### Documentation
- Use Swagger/OpenAPI annotations (`@Operation`, `@Parameter`)
- Provide clear descriptions for operations and parameters

```java
@Operation(summary = "Get account by ID", description = "Returns account details for the given account ID")
@GetMapping("/account/{id}")
public ResponseEntity<Account> getAccount(
        @Parameter(description = "ID of the account to retrieve", required = true) 
        @PathVariable(name = "id") long id) {
    return accountService.getAccountById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
```

### Response Handling
- Use `ResponseEntity<T>` for REST responses
- Return appropriate HTTP status codes
- Use `Optional` for potentially missing data

## Exception Handling

### Custom Exceptions
- Extend `RuntimeException` for business logic exceptions
- Include meaningful error messages
- Use `@Serial` annotation for `serialVersionUID`

```java
public final class ValidationException extends RuntimeException {
    
    @Serial
    private static final long serialVersionUID = 8219814305559421209L;
    
    public ValidationException(String message) {
        super(message);
    }
}
```

### Exception Messages
- Provide clear, user-friendly messages
- Include context information where helpful
- Use consistent message formatting

## Testing Standards

### Test Class Naming
- Suffix test classes with `Test` (e.g., `PhoneNumberValidatorTest`)
- Use `final` for test classes
- Package-private access level

### Test Method Naming
- Use descriptive names that explain the test scenario
- Use camelCase (e.g., `validPhoneNumber`, `nullPhoneNumber`)
- No `test` prefix needed

### Test Structure
- Use JUnit 5 (`@Test` annotation)
- Use AssertJ for assertions (`assertThat()`)
- Use JUnit assertions for exceptions (`assertThrows()`)

```java
@Test
void validPhoneNumber() {
    assertThat(PhoneNumberValidator.isValid("+420123456789", Nationality.CZECH_REPUBLIC)).isTrue();
}

@Test
void nullPhoneNumber() {
    assertThrows(NullPointerException.class,
            () -> PhoneNumberValidator.isValid("+420123456789", null));
}
```

### Test Organization
- Group related tests in the same class
- Use static imports for assertion methods
- Test both positive and negative scenarios

## Documentation

### JavaDoc
- Use JavaDoc for public APIs and complex methods
- Include `@author` and `@since` tags for classes
- Document parameters and return values for complex methods

```java
/**
 * Exception that is thrown when validation fails
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public final class ValidationException extends RuntimeException {
    // implementation
}
```

### Code Comments
- Avoid obvious comments
- Explain complex business logic
- Use TODO comments for temporary solutions

### README and Documentation
- Keep README.md up to date
- Document API endpoints
- Include setup and running instructions

## Best Practices Summary

1. **Favor composition over inheritance**
2. **Use final classes and fields where possible**
3. **Validate constructor parameters with Objects.requireNonNull()**
4. **Follow the Single Responsibility Principle**
5. **Write self-documenting code with clear names**
6. **Test both happy path and edge cases**
7. **Use appropriate Spring annotations**
8. **Follow RESTful API conventions**
9. **Handle exceptions appropriately**
10. **Keep methods small and focused**

This style guide should be followed consistently across the entire codebase to maintain readability, maintainability, and consistency.