🔐 SecureAuth Backend
A Complete Spring Boot Authentication API with JWT, BCrypt & Role-Based Security

This project is a fully functional authentication backend built using Spring Boot 3, Spring Security 6, JWT, and H2 / PostgreSQL.
It supports registration, login, password hashing, JWT token generation, and protected endpoints.

Ideal for learning backend authentication and adding a strong project to your resume.

📌 Features
🔒 Authentication

User Registration

User Login

Password Hashing (BCrypt)

JWT Access Token Generation

JWT Verification on Each Request

Custom UserDetailsService

AuthenticationPrincipal to fetch current user

🛡️ Security

Spring Security 6

JWT Authentication Filter

Route Protection

Public vs Protected APIs

Token-based authorization

🗄️ Database

H2 (in-memory, for development)

PostgreSQL support (for production)

JPA/Hibernate ORM

📁 Project Structure
src/
 └── main/
     └── java/
         └── com.secureauth.secureauth_backend/
             ├── controller/          # REST API controllers
             ├── security/            # SecurityConfig + JWT filter
             ├── util/                # JwtUtil
             ├── model/               # User entity
             └── repository/          # UserRepository

🚀 API Endpoints
Public Endpoints
Method	Endpoint	Description
POST	/api/users	Register user
POST	/api/users/login	Login, get JWT
Protected Endpoints (require token)
Method	Endpoint	Description
GET	/api/users/me	Get authenticated user info

Use this header for protected routes:

Authorization: Bearer <your_jwt_token>

🧪 Example Authentication Flow
1️⃣ Register

POST /api/users

{
  "name": "Jubilee",
  "email": "jubilee@example.com",
  "password": "secret123"
}

2️⃣ Login

POST /api/users/login

{
  "email": "jubilee@example.com",
  "password": "secret123"
}


Response example:

TOKEN : eyJhbGciOiJIUzI1NiJ9...

3️⃣ Access Protected Route

GET /api/users/me

Header:

Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

▶️ Run Locally
Prerequisite

Java 17+

Maven

Steps
git clone https://github.com/<your-username>/secureauth-backend.git
cd secureauth-backend
mvn spring-boot:run


Server runs at:

http://localhost:8080

🛠️ Tech Stack

Java 17

Spring Boot 3

Spring Security 6

JWT (jjwt)

Maven

H2 / PostgreSQL

JPA / Hibernate

📌 Future Enhancements (Roadmap)

✔ React Frontend for Login/Register
✔ Role-based access control
✔ Refresh Token
✔ Forgot Password Flow
✔ User Profile & CRUD
✔ Deployment on Render / Railway / AWS

📜 License

This project is open-source and available under the MIT License.