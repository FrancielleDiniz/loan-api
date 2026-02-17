# Loan API

A simple backend service built in Clojure to simulate loan calculations.  
This project demonstrates clean architecture principles, functional design, and RESTful API implementation using Ring and Reitit.

---

## 🚀 Tech Stack

- Clojure
- Ring (HTTP abstraction)
- Reitit (routing)
- Jetty (embedded server)
- Cheshire (JSON handling)

---

## 🏗 Architecture

The project follows a layered structure:

- **Domain** – Pure business logic (loan calculation)
- **Service** – Application orchestration layer
- **Routes** – HTTP layer and request handling
- **HTTP** – Server configuration and startup
- **Core** – Application entry point

The domain layer is framework-agnostic and fully testable.

```
src/
  loan_api/
    core.clj
    http.clj
    routes.clj
    service.clj
    domain.clj
```

---

## 📌 Endpoint

### POST `/simulate`

### Request

```json
{
  "amount": 10000,
  "rate": 0.02,
  "months": 12
}
```

### Response

```json
{
  "total": 12682.42,
  "installment": 1056.87
}
```

---

## ▶ Running the project

```bash
clj -M -m loan-api.core
```

The server will start at:

```
http://localhost:3000
```

---

## 🧠 Purpose

This project is part of a backend engineering portfolio focused on:

- Functional programming
- Data modeling
- Clean architecture
- Scalable backend systems
- Event-driven extensibility (future Kafka integration)

---

## 📈 Roadmap

- [ ] Input validation middleware
- [ ] Unit tests
- [ ] Kafka event publishing
- [ ] Persistent storage integration
- [ ] Docker support
