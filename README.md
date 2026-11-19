# Hospital Management System

A small Spring Boot REST API for JWT authentication and patient record management. It uses Java 17+, Spring Web, Spring Security, Spring Data JPA, MySQL, JJWT, Lombok, Jakarta Validation, JUnit 5, and Mockito.

## Project structure

```text
src/main/java/com/example/hms/
├── config/       # JWT service, request filter, and Spring Security
├── controller/   # Authentication, patient APIs, and API error handling
├── dto/          # Validated request and response objects
├── entity/       # User, patient, and role persistence models
├── repository/   # Spring Data JPA repositories
├── service/      # Authentication and patient business logic
└── HospitalManagementSystemApplication.java
src/main/resources/application.properties
src/test/java/com/example/hms/  # A matching JUnit test class for each application class
```

## Requirements

- JDK 17 or newer
- Maven 3.9+
- MySQL 8+

Create the local database:

```sql
CREATE DATABASE hospital_db;
```

Set the connection and signing key in the environment. The JWT key must be Base64 encoded and contain at least 32 random bytes.

PowerShell:

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/hospital_db"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "your-local-password"
$env:JWT_SECRET = [Convert]::ToBase64String([Security.Cryptography.RandomNumberGenerator]::GetBytes(32))
```

Bash:

```bash
export DB_URL='jdbc:mysql://localhost:3306/hospital_db'
export DB_USERNAME='root'
export DB_PASSWORD='your-local-password'
export JWT_SECRET="$(openssl rand -base64 32)"
```

Do not commit local credentials or signing keys. Start the application with:

```bash
mvn spring-boot:run
```

The API listens on `http://localhost:8080`. Hibernate creates or updates the tables at startup.

## API endpoints

| Method | Endpoint | Access |
| --- | --- | --- |
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public |
| POST | `/api/patients` | Bearer token required |
| GET | `/api/patients` | Bearer token required |
| GET | `/api/patients/{id}` | Bearer token required |
| PUT | `/api/patients/{id}` | Bearer token required |
| DELETE | `/api/patients/{id}` | Bearer token required |

Register with `{"name":"Navya Raj","email":"navya@example.com","password":"secure-pass"}` and log in with the email and password to receive a token. Send it on patient requests as:

```text
Authorization: Bearer <token>
```

Patient request fields are `name`, `age`, `gender`, `disease`, `address`, and `phoneNumber`. Invalid payloads return HTTP 400 with field-level errors. Missing patients return 404; duplicate email addresses return 409.

## Tests

Tests use JUnit 5 and Mockito. With Maven installed, run:

```bash
mvn test
```

The unit tests do not require a running MySQL server.

## Feature branch workflow

Each feature starts from `develop`, is committed on its own branch, and is merged back into `develop` when complete. Commit messages use `[Navya Raj] : Commit Message Here`.

- `feature/UC1-entity-class`
- `feature/UC2-repository-layer`
- `feature/UC3-dto`
- `feature/UC4-service`
- `feature/UC5-jwt`
- `feature/UC6-controller`

This is a backend-only project; it does not include a browser frontend.
