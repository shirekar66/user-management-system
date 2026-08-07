# User Management System
A production-style **Spring Boot Microservice** for User, Product, and Order Management with secure authentication, role-based authorization, Redis caching, Kafka-based event messaging, and layered architecture.

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
- Kafka Producer & Consumer Integration
- JSON Message Serialization/Deserialization
- Event-Driven Communication (Producer → Kafka → Consumer)
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
- Apache Kafka
- Spring Kafka
- Docker
- Maven
- Lombok

---

## 📋 Prerequisites
Before running the application, install:
- Java 17+
- Maven
- PostgreSQL
- Redis Server
- Docker (for running Kafka)

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

Start Kafka using Docker
```bash
docker compose up -d
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
| Method | Endpoint         |
| ------ | ---------------- |
| POST   | `/auth/register` |
| POST   | `/auth/login`    |

---

### Users
| Method | Endpoint      |
| ------ | ------------- |
| GET    | `/users`      |
| GET    | `/users/{id}` |
| POST   | `/users`      |
| PUT    | `/users/{id}` |
| PATCH  | `/users/{id}` |
| DELETE | `/users/{id}` |

---

### Products
| Method | Endpoint         |
| ------ | ---------------- |
| GET    | `/products`      |
| GET    | `/products/{id}` |
| POST   | `/products`      |
| PUT    | `/products/{id}` |
| DELETE | `/products/{id}` |

---

### Orders
| Method | Endpoint       |
| ------ | -------------- |
| GET    | `/orders`      |
| GET    | `/orders/{id}` |
| POST   | `/orders`      |
| PUT    | `/orders/{id}` |
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
├── kafka
│   ├── producer
│   └── consumer
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

## 📨 Kafka Integration
Apache Kafka is integrated for asynchronous, event-driven communication between application components.

### Setup
- Kafka broker runs locally via **Docker Compose**
- **Spring Kafka** dependency added for producer/consumer support

### Configuration
- **Producer Configuration** — configured with a `ProducerFactory` and `KafkaTemplate` for sending messages
- **Consumer Configuration** — configured with a `ConsumerFactory` and `KafkaListenerContainerFactory` for consuming messages
- **Topic** — dedicated Kafka topic defined for message exchange
- **Consumer Group** — consumers grouped under a common group ID for coordinated message consumption

### Message Flow
```
Producer  →  Kafka Topic  →  Consumer
```
- **Kafka Producer** — publishes events/messages to the configured topic
- **Kafka Consumer** — listens to the topic using `@KafkaListener` and processes incoming messages
- **JSON Serialization** — messages are serialized/deserialized as JSON using `JsonSerializer` / `JsonDeserializer`

### Implemented Components
| Component               | Status |
| ------------------------ | ------ |
| Docker Kafka Setup       | ✅     |
| Spring Kafka Dependency  | ✅     |
| Producer Configuration   | ✅     |
| Kafka Producer           | ✅     |
| Topic                    | ✅     |
| Consumer Configuration   | ✅     |
| Consumer Group           | ✅     |
| Kafka Consumer           | ✅     |
| JSON Serialization       | ✅     |
| Producer → Kafka → Consumer Flow | ✅ |

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
**Java Backend Developer**

Technologies: Java | Spring Boot | Microservices | REST API | Kafka | Redis | PostgreSQL | Docker | Spring Security
