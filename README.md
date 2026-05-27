#  Sportsbook — Sports Betting Backend API

A RESTful application for a sports betting platform built with Spring Boot and Angular . Users can place bets on upcoming football matches, track results, and manage their betting history.

---

## What this does

- Create and manage football teams and matches
- Place bets on matches with custom wager amounts and odds
- Track bet outcomes (Won / Lost / Pending / Voided)
- Auto-calculate potential payouts at the time of bet creation
- Paginated responses on all list endpoints

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| ORM | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Validation | Jakarta Bean Validation |
| Boilerplate | Lombok |
| Build Tool | Maven |
| Server | Embedded Apache Tomcat |

---

## API Endpoints

### Teams — `/api/teams`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/teams` | Get all teams (paginated) |
| GET | `/api/teams/{id}` | Get team by ID |
| POST | `/api/teams` | Create a team |
| PUT | `/api/teams/{id}` | Update a team |
| DELETE | `/api/teams/{id}` | Delete a team |

### Matches — `/api/matches`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/matches` | Get all matches (paginated) |
| GET | `/api/matches/{id}` | Get match by ID |
| POST | `/api/matches` | Create a match |
| PUT | `/api/matches/{id}` | Update a match |
| PATCH | `/api/matches/{id}/result` | Set match result |
| DELETE | `/api/matches/{id}` | Delete a match |

### Bets — `/api/bets`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/bets` | Get all bets (paginated) |
| GET | `/api/bets/{id}` | Get bet by ID |
| POST | `/api/bets` | Place a bet |
| PUT | `/api/bets/{id}` | Update a bet |
| PATCH | `/api/bets/{id}/result` | Settle a bet result |
| DELETE | `/api/bets/{id}` | Delete a bet |

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

### 1. Clone the repo

```bash
git clone https://github.com/genin6382/sportsbook.git
cd sportsbook
```

### 2. Create database

```sql
CREATE DATABASE sportsbook;
```

### 3. Configure `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sportsbook
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Run the app

```bash
./mvnw spring-boot:run
```

Server starts at `http://localhost:8080`

---

## Architecture

The app follows a 3-layer architecture:

```
HTTP Request
     ↓
Controller  →  Validates input, calls service, returns DTO as JSON
     ↓
Service     →  Business logic, converts DTOs ↔ Entities
     ↓
Repository  →  Database operations via Spring Data JPA
     ↓
PostgreSQL
```

---

## What I Learned Building This

- Designing clean REST APIs with proper HTTP verbs and status codes
- JPA relationships (`@ManyToOne`, `@JoinColumn`) vs storing raw IDs
- The DTO pattern — keeping API contracts separate from database entities
- Why indexes matter and which columns to index
- Spring Boot's 3-layer architecture (Controller → Service → Repository)
- How embedded Tomcat works inside a Spring Boot JAR
- CORS configuration for frontend integration

---
