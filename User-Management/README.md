# User Management System

A Spring Boot REST API for user authentication and user/order management.

## Features

- User Registration
- User Login with JWT Authentication
- Role-Based Authorization
- CRUD Operations for Users
- Order Management
- Redis Caching
- Global Exception Handling
- RESTful APIs
- Spring Security Filtering

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- Redis
- PostgreSQL
- Maven

## Prerequisites

- Java 17+
- Maven
- MySQL
- Redis

## Running the Project

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

## API Endpoints

### Authentication

- POST `/auth/register`
- POST `/auth/login`

### Users

- GET `/users`
- GET `/users/{id}`
- POST `/users`
- PUT `/users/{id}`
- DELETE `/users/{id}`

### Orders

- POST `/orders`
- GET `/orders`

## Project Structure

```
src
 ├── controller
 ├── service
 ├── repositories
 ├── entity
 ├── dto
 ├── security
 ├── config
 └── exception
```

## Author

**Sachin Hirekar**