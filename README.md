# Digital Cinema Backend

## Overview

This project is the backend for a Digital Cinema booking system built with Spring Boot.

Current implementation includes:

- Week 1: Project setup and database connectivity
- Week 2: Persistence layer (Liquibase, JPA entities, repositories)
- Week 3: Business logic and REST API layer
- Week 4: Deep Testing Architecture & Production Readiness

---

# Week 2 Implementation

## 1. Liquibase and JPA Configuration

Implemented in:

`src/main/resources/application.yaml`

Features:

- Liquibase enabled using `classpath:/db/db.changelog.xml`
- `default-schema` and `liquibase-schema` configured as `base_schema`
- HikariCP configured with `schema: base_schema`
- Automatic schema creation on first connection
- Hibernate validation enabled using:

```yaml
spring.jpa.hibernate.ddl-auto=validate
```

## 2. Liquibase Changelog

Implemented:

- `db/db.changelog.xml`
- `db/changelog/init_script.xml`
- `db/changelog/init_script.sql`

Database objects:

- cinemas
- halls
- movies
- movie_genres
- price_category
- seats
- sessions
- session_seats

including indexes and foreign key constraints.

## 3. Enum Classes

Implemented in:

`src/main/java/com/itpu/internship2/digital_cinema/util`

- AgeRating
- Genre
- MovieLang
- MovieFormat
- PriceCategory
- SeatStatus

## 4. JPA Entities

Implemented:

- CinemaEntity
- HallEntity
- MovieEntity
- MovieGenreEntity
- MovieGenreId
- PriceCategoryEntity
- SeatEntity
- SessionEntity
- SessionSeatEntity

Implemented relationships:

- Cinema → Hall
- Hall → Seat
- Hall → Session
- Movie → Session
- Movie → MovieGenre
- PriceCategory → Seat
- Session → SessionSeat
- Seat → SessionSeat

## 5. Repository Layer

Implemented Spring Data JPA repositories:

- CinemaRepository
- HallRepository
- MovieRepository
- MovieGenreRepository
- PriceCategoryRepository
- SeatRepository
- SessionRepository
- SessionSeatRepository

---

# Week 3 Implementation

## 1. REST Controllers

Implemented CRUD REST controllers for:

- Movie
- Seat
- Session
- SessionSeat

Features:

- RESTful endpoints
- ResponseEntity responses
- Pagination support
- Bean Validation
- OpenAPI annotations

## 2. DTO Layer

Implemented request and response DTOs:

- SaveMovieDTO / GetMovieDTO
- SaveSeatDTO / GetSeatDTO
- SaveSessionDTO / GetSessionDTO
- SaveSessionSeatDTO / GetSessionSeatDTO

Features:

- Bean Validation
- Swagger documentation
- Separation of API models from JPA entities

## 3. Mapper Layer

Implemented manual mapper classes:

- MovieMapper
- SeatMapper
- SessionMapper
- SessionSeatMapper

Responsibilities:

- Entity → DTO
- DTO → Entity
- Entity update from DTO

## 4. Service Layer

Implemented business services:

- MovieService
- SeatService
- SessionService
- SessionSeatService

Features:

- Transaction management
- Business logic
- Entity loading
- DTO mapping
- Logging
- Exception handling

## 5. Global Exception Handling

Implemented:

- ApiException
- ResourceNotFoundException
- ResourceAlreadyExistsException
- BusinessException
- ValidationException
- GlobalExceptionHandler

Features:

- Consistent REST error responses
- HTTP status mapping
- Validation error handling

## 6. Swagger / OpenAPI

Configured OpenAPI documentation with:

- SpringDoc OpenAPI
- Swagger UI
- Controller documentation
- DTO schema documentation

Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

# Testing

Implemented comprehensive automated testing including:

- Service unit tests
- Controller tests using MockMvc
- Mapper unit tests
- GlobalExceptionHandler tests
- Shared TestDataFactory

Test suite includes:

- JUnit 5
- Mockito
- Spring Boot Test
- MockMvc
- JaCoCo code coverage
- **Allure Framework** (for extensive metrics reporting)

## Testing Architecture Metrics

The testing framework was dramatically enhanced during Week 4, yielding a highly segregated, enterprise-grade architecture:

- **Unit Tests (151 Tests)**: Executed by `maven-surefire-plugin` (`mvn test`). Focuses on Service logic, MapStruct conversions, `@DataJpaTest` Repository slicing, and intense Edge-Case/Boundary Valuations utilizing `ValidatorFactory` and Equivalence Partitioning.
- **Integration Tests (3 Tests)**: Executed by `maven-failsafe-plugin` (`mvn verify`). Uses isolated `*IT.java` configurations bootstrapping the full Spring container to test end-to-end API logic.

---

# Tech Stack

- Java 21
- Spring Boot 3.5.15
- Spring Data JPA
- Spring Validation
- SpringDoc OpenAPI
- Liquibase
- PostgreSQL
- Maven
- Lombok
- JUnit 5
- Mockito
- MockMvc
- JaCoCo
- Allure Framework

---

# Build & Verification

Successful verification includes:

- `mvn clean compile`
- `mvn clean test`
- `mvn clean install`
- Liquibase migrations
- Spring Boot application startup
- Swagger UI generation
- Automated test suite execution

---

# Project Structure

```
src/main/java/com/itpu/internship2/digital_cinema
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── service
└── util

src/test/java/com/itpu/internship2/digital_cinema
├── controller
├── exception
├── fixture
├── mapper
└── service
```

---

# Current Status

✅ Week 1 completed

✅ Week 2 completed

✅ Week 3 completed

✅ Week 4 completed

The backend project has officially completed all development phases. It boasts a fully functional persistence layer, robust business logic, a documented OpenAPI spec, centralized exception handling, and a highly resilient 150+ test suite powered by Allure. 

🚀 **The backend is stable, completely finished, and fully ready for seamless frontend integration.**