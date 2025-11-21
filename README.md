# 🔐 SecureAuth Backend  
**A Complete Spring Boot Authentication API with JWT, BCrypt & Role-Based Security**

This project is a fully functional authentication backend built using **Spring Boot 3**, **Spring Security 6**, **JWT**, and **H2 / PostgreSQL**.  
It supports registration, login, password hashing, JWT generation, and protected endpoints — ideal for a resume/project demo.

---

## 🚀 Features

**Authentication**
- User registration (POST `/api/users`)
- User login (POST `/api/users/login`)
- Password hashing (BCrypt)
- JWT access token generation and verification
- `UserDetailsService` and `AuthenticationPrincipal` support

**Security**
- Spring Security 6
- JWT authentication filter
- Route protection (public vs protected endpoints)

**Database**
- H2 (in-memory) for development
- PostgreSQL ready for production
- JPA / Hibernate

---

## 📁 Project Structure (important files)

```
src/
└── main/
    └── java/
        └── com.secureauth.secureauth_backend/
            ├── controller/          # REST controllers (UserController)
            ├── security/            # SecurityConfig, Jwt filter, CustomUserDetailsService
            ├── util/                # JwtUtil
            ├── model/               # User entity
            └── repository/          # UserRepository
```



---

## 🛠️ Quick Start (run locally)

**Prerequisites**
- Java 17+
- Maven

**Run**
```bash
git clone https://github.com/<your-username>/secureauth-backend.git
cd secureauth-backend
mvn clean spring-boot:run


The app starts at: http://localhost:8080

📬 API Endpoints
Public

POST /api/users — Register user
Example request body:

{
  "name": "Jubilee",
  "email": "jubilee@example.com",
  "password": "secret123"
}


POST /api/users/login — Login (returns JWT token)
Example request body:

{
  "email": "jubilee@example.com",
  "password": "secret123"
}

Protected (requires Authorization: Bearer <token>)

GET /api/users/me — Return authenticated user info

Header for protected requests

Authorization: Bearer <your_jwt_token>