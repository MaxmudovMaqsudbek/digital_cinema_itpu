# Digital Cinema Backend

## Overview

This project is the backend for a Digital Cinema booking system built with Spring Boot.

Current state covers:

- Session 1: project setup and database connectivity
- Session 2: data layer and persistence (Liquibase + JPA entities + repositories)

## Week 2 (Session 2) Implementation

### 1) Liquibase and JPA Configuration

Implemented in `src/main/resources/application.yaml`:

- Liquibase is enabled with changelog path `classpath:/db/db.changelog.xml`
- `default-schema` and `liquibase-schema` are set to `base_schema`
- Hikari uses `schema: base_schema`
- Hikari auto-creates schema on first connection:
    `connection-init-sql: CREATE SCHEMA IF NOT EXISTS base_schema`
- JPA validates mappings against DB schema:
    `spring.jpa.hibernate.ddl-auto: validate`

### 2) Liquibase Changelog

Implemented files:

- `src/main/resources/db/db.changelog.xml`
- `src/main/resources/db/changelog/init_script.xml`
- `src/main/resources/db/changelog/init_script.sql`

The SQL changelog creates all required tables and indexes in `base_schema`:

- `cinemas`
- `halls`
- `movies`
- `movie_genres`
- `price_category`
- `seats`
- `sessions`
- `session_seats`

### 3) Enum Classes

Implemented in `src/main/java/com/itpu/internship2/digital_cinema/util`:

- `AgeRating`
- `Genre`
- `MovieLang`
- `MovieFormat`
- `PriceCategory`
- `SeatStatus`

### 4) JPA Entities and Relationships

Implemented in `src/main/java/com/itpu/internship2/digital_cinema/entity`:

- `CinemaEntity`
- `HallEntity`
- `MovieEntity`
- `MovieGenreEntity`
- `MovieGenreId`
- `PriceCategoryEntity`
- `SeatEntity`
- `SessionEntity`
- `SessionSeatEntity`

Implemented relationships:

- Cinema 1:N Hall
- Hall 1:N Seat
- Hall 1:N Session
- Movie 1:N Session
- Movie 1:N MovieGenre
- PriceCategory 1:N Seat
- Session 1:N SessionSeat
- Seat 1:N SessionSeat

### 5) Repository Interfaces

Implemented in `src/main/java/com/itpu/internship2/digital_cinema/repository`:

- `CinemaRepository`
- `HallRepository`
- `MovieRepository`
- `MovieGenreRepository`
- `PriceCategoryRepository`
- `SeatRepository`
- `SessionRepository`
- `SessionSeatRepository`

## Tech Stack

- Java 21
- Spring Boot 3.5.15
- Spring Data JPA
- Liquibase
- PostgreSQL
- Maven
- Lombok

## Validation Status

- `mvn clean compile` passed
- `mvn test` passed
- Liquibase migration runs successfully
- Spring Data JPA detects all implemented repositories

## Next Planned Scope

Session 3 focuses on business logic and API layer:

- services and transactions
- DTOs and mappers
- REST controllers
- validation and error handling
