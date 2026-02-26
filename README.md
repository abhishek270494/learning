# Learning Spring Boot Project

## Overview

This project is a Spring Boot application that demonstrates a RESTful API for managing Todo items and integrates with Apache Kafka for messaging. It uses PostgreSQL as the primary database and supports running in Docker containers with a full development stack (Spring Boot app, PostgreSQL, Kafka, Zookeeper, Keycloak for authentication, and Jenkins for CI/CD) orchestrated via Docker Compose.

## Features

- RESTful API for CRUD operations on Todo items
- Kafka integration for producing and consuming messages
- PostgreSQL database integration via Spring Data JPA
- Exception handling with custom exceptions
- Docker and Docker Compose support for easy setup
- Profiles for local and Docker environments
- Basic test setup with JUnit and Spring Boot Test
- Keycloak integration for authentication and authorization
- Jenkins CI/CD pipeline configuration

## Project Structure

```
learning/
├── compose.yaml                # Docker Compose configuration for app, DB, Kafka, Zookeeper, Keycloak, Jenkins
├── dockerfile                  # Multi-stage Dockerfile for building and running the app
├── Jenkinsfile                 # Jenkins pipeline definition for CI/CD
├── jenkins/
│   └── Dockerfile              # Jenkins custom Docker image (if used)
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
│   │       ├── application-docker.yaml         # Docker profile config
│   │       └── keycloak backup/                # Keycloak realm and user JSON files
│   │           ├── master-realm.json
│   │           ├── master-users-0.json
│   │           ├── spring-realm-realm.json
│   │           └── spring-realm-users-0.json
│   └── test/java/com/example/learning/
│       └── LearningApplicationTests.java       # Basic context load test
└── ...
```

## Technologies & Dependencies

- Java 17
- Spring Boot 4.x
  - Spring Web MVC
  - Spring Data JPA
  - Spring Security (with Keycloak integration)
  - Spring for Apache Kafka
- PostgreSQL
- Apache Kafka & Zookeeper
- Keycloak (for authentication and authorization)
- Jenkins (for CI/CD)
- Docker & Docker Compose
- Lombok (for boilerplate code reduction)
- JUnit 5 (for testing)

## Getting Started

### Prerequisites
- Docker & Docker Compose installed
- Java 17 and Maven (for local development)

### Environment Variables

You may need to configure the following environment variables (or update the YAML configs):

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `KAFKA_BOOTSTRAP_SERVERS`
- `KEYCLOAK_AUTH_SERVER_URL`
- `KEYCLOAK_REALM`
- `KEYCLOAK_RESOURCE` (client ID)
- `KEYCLOAK_CREDENTIALS_SECRET` (if using confidential client)

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
   - Keycloak (port 8008, default admin: admin/admin)
   - Jenkins (port 8090, default admin: admin/admin if configured)
   - Spring Boot app (port 8080)

3. The API will be available at `http://localhost:8080`
4. Keycloak will be available at `http://localhost:8008/auth` (or as configured)
5. Jenkins will be available at `http://localhost:8090` (or as configured)

### Keycloak Setup

- The `src/main/resources/keycloak backup/` directory contains example realm and user JSON files for Keycloak import.
- On first run, you can import these files into Keycloak via the admin console to quickly set up realms, clients, and users for development.
- Update your application YAML files to match the Keycloak realm, client, and endpoints as needed.
- **Keycloak default port is 8008.**

### Jenkins CI/CD

- The project includes a `Jenkinsfile` that defines the CI/CD pipeline for building, testing, and deploying the application.
- A custom Jenkins Docker image can be built using the `jenkins/Dockerfile` if you want to extend Jenkins with additional plugins or tools.
- Jenkins can be run as part of the Docker Compose stack (see `compose.yaml`).
- Access Jenkins at `http://localhost:8090` (default admin: admin/admin if configured).
- The pipeline typically includes steps for Maven build, running tests, and building Docker images.

### Running Locally (without Docker)

1. Ensure PostgreSQL, Kafka, and Keycloak are running locally and update `src/main/resources/application-local.yaml` accordingly.
2. Build and run the app:
   ```sh
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
   ```

## API Endpoints

### Todo API
- `GET /api/todos` — List all todos (requires authentication)
- `GET /api/todos/{id}` — Get todo by ID (requires authentication)
- `POST /api/todos` — Create new todo (requires authentication)
- `PUT /api/todos/{id}` — Update existing todo (requires authentication)
- `DELETE /api/todos/{id}` — Delete todo (requires authentication)

### Kafka API
- `POST /kafka/send?message=Hello` — Send a message to Kafka (requires authentication)
- `GET /kafka/messages` — Retrieve consumed messages (requires authentication)

> **Note:** All endpoints are protected by Keycloak authentication. Obtain a valid access token from Keycloak and include it in the `Authorization: Bearer <token>` header for API requests.

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

---

**Contact:**  
For questions or support, please open an issue or contact the maintainer.
