# User Management System

A production-style **Spring Boot Microservice** for User, Product, and Order Management with secure authentication, role-based authorization, Redis caching, and layered architecture.

---

## 🚀 Features

- JWT Authentication & Authorization
- User Registration & Login
- Role-Based Access Control (RBAC)
- CRUD Operations for Users
- CRUD Operations for Products
- CRUD Operations for Orders
- RESTful API Design
- DTO Pattern
- Entity-DTO Mapping
- Global Exception Handling
- Custom Exception Handling
- Bean Validation
- Redis Caching
- Spring Cache Abstraction
- Transaction Management
- Spring Security Filters
- Code Refactoring & Clean Architecture

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JSON Web Token)
- PostgreSQL
- Redis
- Spring Cache
- Maven
- Lombok

---

## 📋 Prerequisites

Before running the application, install:

- Java 17+
- Maven
- PostgreSQL
- Redis Server

---

## ▶ Running the Application

Clone the repository

```bash
git clone https://github.com/your-username/user-management-system.git
```

Navigate to the project

```bash
cd user-management-system
```

Build the project

```bash
mvn clean install
```

Run the application

```bash
mvn spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

---

## 📌 REST API Endpoints

### Authentication

| Method | Endpoint |
|---------|----------|
| POST | `/auth/register` |
| POST | `/auth/login` |

---

### Users

| Method | Endpoint |
|---------|----------|
| GET | `/users` |
| GET | `/users/{id}` |
| POST | `/users` |
| PUT | `/users/{id}` |
| PATCH | `/users/{id}` |
| DELETE | `/users/{id}` |

---

### Products

| Method | Endpoint |
|---------|----------|
| GET | `/products` |
| GET | `/products/{id}` |
| POST | `/products` |
| PUT | `/products/{id}` |
| DELETE | `/products/{id}` |

---

### Orders

| Method | Endpoint |
|---------|----------|
| GET | `/orders` |
| GET | `/orders/{id}` |
| POST | `/orders` |
| PUT | `/orders/{id}` |
| DELETE | `/orders/{id}` |

---

## 📂 Project Structure

```
src
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repositories
├── security
├── service
│   └── impl
└── UserManagementApplication.java
```

---

## 🏗 Architecture

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
PostgreSQL

DTO ↔ Mapper ↔ Entity

↓

Redis Cache

↓

Global Exception Handler
```

---

## ✅ Validation & Error Handling

- Bean Validation using `@Valid`
- Custom Exceptions
- Global Exception Handler using `@RestControllerAdvice`
- Standardized API Error Responses

---

## ⚡ Caching

Redis is used to improve application performance by caching frequently accessed data.

Implemented using:

- `@Cacheable`
- `@CachePut`
- `@CacheEvict`

---

## 🔒 Security

- Spring Security
- JWT Authentication
- Role-Based Authorization
- Password Encryption

---

## 👨‍💻 Author

**Sachin Hirekar**
