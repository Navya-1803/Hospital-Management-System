# Hospital Management System

A backend REST API built using Spring Boot for user authentication and patient record management. The application uses JWT-based authentication to secure patient APIs.

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JJWT)
- Lombok
- Jakarta Validation
- JUnit 5
- Mockito
- Maven

## Features

- User registration and login
- JWT-based authentication
- Secure patient record management
- Create, view, update, and delete patient records
- Request validation
- Centralized exception handling
- Unit testing using JUnit and Mockito

## Project Structure

```text
src/main/java/com/example/hms/
├── config/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
└── HospitalManagementSystemApplication.java
```

## Requirements

- JDK 17 or newer
- Maven 3.9+
- MySQL 8+

## Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE hospital_db;
```

## Environment Variables

Configure the following environment variables before running the application:

| Variable | Description |
|---|---|
| `DB_URL` | MySQL database connection URL |
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |
| `JWT_SECRET` | Base64-encoded JWT signing key (at least 32 random bytes) |

### PowerShell

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/hospital_db"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "your-local-password"
$env:JWT_SECRET = [Convert]::ToBase64String([Security.Cryptography.RandomNumberGenerator]::GetBytes(32))
```

### Bash

```bash
export DB_URL='jdbc:mysql://localhost:3306/hospital_db'
export DB_USERNAME='root'
export DB_PASSWORD='your-local-password'
export JWT_SECRET="$(openssl rand -base64 32)"
```

Do not commit database credentials or JWT signing keys.

## Running the Application

```bash
mvn spring-boot:run
```

The application runs at:

```text
http://localhost:8080
```

## API Endpoints

| Method | Endpoint | Access |
|---|---|---|
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public |
| POST | `/api/patients` | Authenticated |
| GET | `/api/patients` | Authenticated |
| GET | `/api/patients/{id}` | Authenticated |
| PUT | `/api/patients/{id}` | Authenticated |
| DELETE | `/api/patients/{id}` | Authenticated |

### Authentication

Register a user:

```json
{
  "name": "Navya Raj",
  "email": "navya@example.com",
  "password": "secure-pass"
}
```

Log in using the registered email and password to receive a JWT token.

Include the token in authenticated requests:

```http
Authorization: Bearer <token>
```

### Patient Fields

| Field | Description |
|---|---|
| `name` | Patient name |
| `age` | Patient age |
| `gender` | Patient gender |
| `disease` | Patient diagnosis or disease |
| `address` | Patient address |
| `phoneNumber` | Patient contact number |

## Error Handling

| HTTP Status | Description |
|---|---|
| 400 | Invalid request data |
| 404 | Patient not found |
| 409 | Email already exists |

## Running Tests

```bash
mvn test
```

Unit tests use JUnit 5 and Mockito and do not require a running MySQL server.

## Feature Branches

| Branch | Description |
|---|---|
| `feature/UC1-entity-class` | Creates the JPA entity classes for the application, including User, Patient, and Role, and defines their database mappings. |
| `feature/UC2-repository-layer` | Implements the repository layer using Spring Data JPA for database operations on users and patients. |
| `feature/UC3-dto` | Adds request and response DTOs for the API, including validation rules for incoming data. |
| `feature/UC4-service` | Implements the business logic for user authentication and patient record management. |
| `feature/UC5-jwt` | Implements JWT generation, validation, authentication filtering, and the Spring Security configuration. |
| `feature/UC6-controller` | Implements the REST controllers for authentication and patient APIs, including centralized API exception handling. |

## Branch Workflow

Each feature is developed on its own branch from `develop` and merged back into `develop` after completion.

Commit message format:

```text
[Navya Raj] : Commit Message Here
```

