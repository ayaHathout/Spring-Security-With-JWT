# Spring Security With JWT

A demo Spring Boot project showcasing how to implement **authentication and authorization using Spring Security and JWT (JSON Web Tokens)**.

This project demonstrates a simple and practical way to secure REST APIs in a Spring Boot application by issuing JWT tokens for:

- **User Registration (`/auth/register`)**
- **User Login (`/auth/login`)**
- **Protected Endpoints** that require a Bearer JWT token

The application uses:
- **Spring Boot**
- **Spring Security**
- **JSON Web Token (JWT)**
- **PostgreSQL** for data persistence
- **Stateless authentication** with custom JWT filter

---

## 🚀 Features

✔ Register new users  
✔ Login and receive a JWT token  
✔ Secure endpoints with JWT-based authentication  
✔ Custom JWT Authentication Filter to validate tokens  
✔ Stateless, scalable security for REST API  
✔ Password encoding with BCrypt

---

## 🏗 Architecture

The application follows a layered architecture:

Controller → Service → Repository → Database

Authentication is handled using a custom JWT filter that validates tokens before accessing secured endpoints.

---

## 🧪 Endpoints

- `POST /auth/register` — Register a new user  
- `POST /auth/login` — Login and generate JWT token  
- `GET /demo` — Protected endpoint requiring a valid JWT token

---

## 🔧 How to Run

1. Clone the repository  
2. Configure PostgreSQL credentials in `application.properties`  
3. Run the application:

   ```bash
   mvn spring-boot:run
4. Test the API using Postman or any REST client
  - Register → Login → Use JWT token as Bearer in Authorization header

---

## 📌 Notes

- JWT tokens are generated and validated using a secret key configured in application.properties.
- This demo can be used as a base for more advanced security features like roles, refresh tokens, and custom claims.
