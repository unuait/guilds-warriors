
# ⚔️ Guilds & Warriors API
> A production-structured Spring Boot REST API for managing guilds and warriors, built to demonstrate clean backend architecture, service-layer design, and real-world API development practices.

---

## 🚀 Project Overview
Guilds & Warriors is a backend-focused REST API built with Spring Boot that models a simple domain of guilds and warriors while emphasizing **clean architecture**, **maintainable code structure**, and **real backend engineering patterns**.

This project was built to practice and demonstrate how production-style backend systems are designed:

- layered architecture
- DTO-based request/response flow
- service abstraction
- JPA persistence
- full & partial updates
- integration testing

Rather than building around complexity, this project focuses on doing the fundamentals the right way.

---

## ✨ Why This Project Matters
Most beginner CRUD projects stop at “it works.”

This one was built to go further:

- separate transport models from persistence models
- isolate business logic from controllers
- test real HTTP behavior with integration tests
- handle full vs partial updates correctly
- keep the codebase scalable and easy to extend

This project reflects how backend APIs are structured in real applications — not just how they are made to work.

---

## 🧱 Architecture
The application follows a layered architecture designed around separation of concerns.

```text
Client
  │
  ▼
Controller Layer
  │
  ▼
Service Layer
  │
  ▼
Repository Layer
  │
  ▼
MySQL Database
```
Layer Responsibilities:

-Controller Layer:

Handles incoming HTTP requests and returns HTTP responses.
Responsibilities:
- endpoint exposure
- request/response handling
- status code control
- delegating work to services

-Service Layer:

Contains business logic and application rules.
Responsibilities:
- orchestrating operations
- validation of business flow
- update/patch logic
- repository coordination

-Repository Layer:

Handles persistence and database interaction.
Responsibilities:
- CRUD operations
- JPA query abstraction
- entity persistence

-DTO Layer

Separates API payloads from internal persistence models.
Responsibilities:
- safe request/response transport
- API contract isolation
- prevents direct entity exposure

-Mapper Layer

Converts DTOs into Entities and Entities into DTOs.
Responsibilities:
- clean transformation between layers
- isolates mapping logic from business logic

🧭 Request Flow
A request moves through the system like this:
```text
HTTP Request
   │
   ▼
Controller
   │
   ▼
DTO → Entity Mapping
   │
   ▼
Service Logic
   │
   ▼
Repository (JPA)
   │
   ▼
MySQL
   │
   ▼
Entity → DTO Mapping
   │
   ▼
HTTP Response
```
This keeps each layer focused on a single responsibility and avoids coupling API logic directly to persistence.

🛠️ Tech Stack

| Technology      | Purpose                 |
| --------------- | ----------------------- |
| Java 17         | Core language           |
| Spring Boot     | Application framework   |
| Spring Web      | REST API layer          |
| Spring Data JPA | Persistence abstraction |
| Hibernate       | ORM                     |
| MySQL           | Relational database     |
| Gradle          | Build tool              |
| JUnit 5         | Testing                 |
| MockMvc         | Integration testing     |


📡 API Endpoints
🏰 Guilds
| Method | Endpoint       | Description                        |
| ------ | -------------- | ---------------------------------- |
| GET    | `/guilds`      | Retrieve all guilds                |
| GET    | `/guilds/{id}` | Retrieve a guild by ID             |
| POST   | `/guilds`      | Create a new guild                 |
| PUT    | `/guilds/{id}` | Fully replace an existing guild    |
| PATCH  | `/guilds/{id}` | Partially update an existing guild |
| DELETE | `/guilds/{id}` | Delete a guild                     |
  

⚔️ Warriors
| Method | Endpoint         | Description            |
| ------ | ---------------- | ---------------------- |
| GET    | `/warriors`      | Retrieve all warriors  |
| GET    | `/warriors/{id}` | Retrieve warrior by ID |
| POST   | `/warriors`      | Create a new warrior   |
| PUT    | `/warriors/{id}` | Fully update a warrior |
| DELETE | `/warriors/{id}` | Delete a warrior       |

🔄 Update Semantics
One of the main focuses of this project is handling update behavior correctly.

PUT — Full Update
Replaces the entire resource.
- expects a full payload
- overwrites all fields
- used when replacing the full entity state

PATCH — Partial Update
Updates only provided fields.
- ignores missing fields
- preserves existing values
- used for safe partial modification
This distinction is implemented and tested explicitly.

🧪 Testing Strategy
This project includes integration tests to validate:
- real endpoint behavior
- request/response correctness
- persistence integrity
- update logic (PUT vs PATCH)
- HTTP status handling

What is tested
- controllers through MockMvc
- service-to-database integration
- repository persistence behavior
- update workflows
This ensures the API is tested as a real running system, not just in isolated units.

📁 Project Structure
```Text
src
├── main
│   ├── java/com/younait/guilds
│   │   ├── controllers
│   │   ├── services
│   │   │   └── impl
│   │   ├── repositories
│   │   ├── domain
│   │   │   ├── DTO
│   │   │   └── Entities
│   │   ├── mappers
│   │   │   └── impl
│   │   └── config
│   └── resources
│
└── test
    ├── controllers
    ├── repositories
    └── resources
```
▶️ Running the Project
Clone the repository
```Text
git clone https://github.com/unuait/guilds-warriors.git
cd guilds-warriors
```

Configure MySQL
Update application.properties with your local credentials:
git clone https://github.com/unuait/guilds-warriors.git
cd guilds-warriors
```Text
spring.datasource.url=jdbc:mysql://localhost:3306/warriorsdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```
🔮 Future Improvements

Planned improvements to evolve this into a more production-ready backend:

- JWT Authentication & Authorization
- Role-based access control
- Request validation
- Global exception handling
- Docker support
- CI/CD pipeline

📌 Notes

This project was built as part of a backend engineering learning journey with an emphasis on writing cleaner code, understanding architecture, and building systems the right way.

It is intentionally simple in domain, but serious in structure.
- frontend client (React)
