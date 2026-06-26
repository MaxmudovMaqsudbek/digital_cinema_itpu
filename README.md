# Digital Cinema - Session 1: Project Setup vs Configuration

## Overview

This project is the backend implementation of a **Digital Cinema Booking System** developed using **Spring Boot**. Session 1 focuses on setting up the project, configuring the development environment, and verifying database connectivity.

## Completed Tasks

* Generated the project using **Spring Initializr**.
* Added required dependencies:

    * Spring Web
    * Spring Data JPA
    * PostgreSQL Driver
    * Lombok
* Organized the project structure:

    * `controller`
    * `service`
    * `repository`
    * `entity`
    * `config`
* Configured a **PostgreSQL** database.
* Configured application settings using `application.yml`.
* Used environment variables for sensitive database credentials.
* Successfully started the Spring Boot application.
* Verified the connection between the application and the PostgreSQL database.

## Technologies

* Java 17
* Spring Boot 3.5.16
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok

## Project Structure

```
src
├── main
│   ├── java
│   │   └── controller
│   │   └── service
│   │   └── repository
│   │   └── entity
│   │   └── config
│   └── resources
│       └── application.yml
```

## Status

✅ Session 1 completed successfully. The project is configured, connected to PostgreSQL, and ready for implementing entities and persistence in Session 2.
