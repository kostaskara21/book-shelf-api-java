# Book Shelf API

A Spring Boot microservice that provides CRUD operations for managing books. The project uses DTOs and mappers for clean entity transformation, secures its endpoints with JWT-based authentication, and supports pagination for handling large data sets. PostgreSQL is used as the database, run via Docker Compose.

## Tech Stack

- **Java 17**
- **Spring Boot 3.4.4** (Web, Data JPA, Security)
- **JWT** for authentication
- **PostgreSQL** as the database
- **springdoc-openapi** for Swagger / OpenAPI documentation
- **Docker Compose** for local database setup
- **Lombok** for boilerplate reduction

## Prerequisites

Before running the project, make sure you have the following installed:

- [Java 17 (JDK)](https://adoptium.net/)
- [Maven](https://maven.apache.org/) (or use the included Maven wrapper, if present)
- [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/)
- [Git](https://git-scm.com/)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/kostaskara21/book-shelf-api-java.git
cd book-shelf-api-java
```

### 2. Start the database with Docker Compose

This spins up a PostgreSQL container for the application to connect to:

```bash
docker compose up -d
```

> Use `docker-compose up -d` instead if you're on an older Docker Compose version.

This will start a PostgreSQL instance using the configuration defined in `docker-compose.yaml`.

To stop the database container:

```bash
docker compose down
```

## API Documentation (Swagger)

This project uses **springdoc-openapi**, so once the application is running, all available endpoints, request/response models, and authentication requirements can be explored directly through Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

From there you can:
- Browse all available endpoints
- Authenticate using a JWT token (via the **Authorize** button) to test secured endpoints
- Try out requests directly from the browser


## Authentication


Most endpoints are secured with JWT-based authentication. Use the authentication endpoint(s) exposed in Swagger UI to obtain a token, then click **Authorize** in Swagger UI and paste the token to access protected routes.
