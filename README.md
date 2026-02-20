# Learning Spring Boot Project

## Overview

This project is a Spring Boot application that demonstrates a RESTful API for managing Todo items and integrates with Apache Kafka for messaging. It uses PostgreSQL as the primary database and supports running in Docker containers with a full development stack (Spring Boot app, PostgreSQL, Kafka, Zookeeper) orchestrated via Docker Compose.

## Features

- RESTful API for CRUD operations on Todo items
- Kafka integration for producing and consuming messages
- PostgreSQL database integration via Spring Data JPA
- Exception handling with custom exceptions
- Docker and Docker Compose support for easy setup
- Profiles for local and Docker environments
- Basic test setup with JUnit and Spring Boot Test

## Project Structure

```
learning/
├── compose.yaml                # Docker Compose configuration for app, DB, Kafka, Zookeeper
├── dockerfile                  # Multi-stage Dockerfile for building and running the app
├── pom.xml                     # Maven build configuration and dependencies
├── src/
│   ├── main/
│   │   ├── java/com/example/learning/
│   │   │   ├── LearningApplication.java         # Main Spring Boot entry point
│   │   │   ├── controller/
│   │   │   │   ├── KafkaController.java         # REST endpoints for Kafka messaging
│   │   │   │   └── TodoController.java          # REST endpoints for Todo CRUD
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java  # Centralized exception handling
│   │   │   │   └── TodoNotFoundException.java   # Custom exception for missing Todos
│   │   │   ├── model/
│   │   │   │   └── Todo.java                   # JPA entity for Todo items
│   │   │   ├── repository/
│   │   │   │   └── ITodoRepository.java        # Spring Data JPA repository
│   │   │   └── service/
│   │   │       ├── KafkaConsumerService.java   # Kafka consumer logic
│   │   │       ├── KafkaProducerService.java   # Kafka producer logic
│   │   │       └── KafkaTopicConfig.java       # Kafka topic configuration
│   │   └── resources/
│   │       ├── application.yaml                # Main Spring Boot config
│   │       ├── application-local.yaml          # Local profile config
│   │       └── application-docker.yaml         # Docker profile config
│   └── test/java/com/example/learning/
│       └── LearningApplicationTests.java       # Basic context load test
└── ...
```

## Technologies & Dependencies

- Java 17
- Spring Boot 4.x
  - Spring Web MVC
  - Spring Data JPA
  - Spring Security (basic setup)
  - Spring for Apache Kafka
- PostgreSQL
- Apache Kafka & Zookeeper
- Docker & Docker Compose
- Lombok (for boilerplate code reduction)
- JUnit 5 (for testing)

## Getting Started

### Prerequisites
- Docker & Docker Compose installed
- Java 17 and Maven (for local development)

### Running with Docker Compose

1. Clone the repository:
   ```sh
   git clone <repo-url>
   cd learning
   ```
2. Build and start all services:
   ```sh
   docker compose up --build
   ```
   This will start:
   - PostgreSQL (port 5432)
   - Zookeeper (port 2181)
   - Kafka (port 9092)
   - Spring Boot app (port 8080)

3. The API will be available at `http://localhost:8080`

### Running Locally (without Docker)

1. Ensure PostgreSQL and Kafka are running locally and update `src/main/resources/application-local.yaml` accordingly.
2. Build and run the app:
   ```sh
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
   ```

## API Endpoints

### Todo API
- `GET /api/todos` — List all todos
- `GET /api/todos/{id}` — Get todo by ID
- `POST /api/todos` — Create new todo
- `PUT /api/todos/{id}` — Update existing todo
- `DELETE /api/todos/{id}` — Delete todo

### Kafka API
- `POST /kafka/send?message=Hello` — Send a message to Kafka
- `GET /kafka/messages` — Retrieve consumed messages

## Testing

Run tests with Maven:
```sh
./mvnw test
```

## Contribution Guidelines

1. Fork the repository and create your branch from `main`.
2. Ensure code passes all tests and follows project conventions.
3. Submit a pull request with a clear description of your changes.

## License

This project does not currently specify a license. Please add a license file if you intend to open source this project.

