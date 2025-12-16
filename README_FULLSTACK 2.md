# Bangla Learning LMS - Full-Stack Application

A comprehensive Learning Management System (LMS) for learning the Bengali language, built with React (Frontend) and Spring Boot (Backend).

## 📋 Table of Contents

- [Project Architecture](#project-architecture)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [Features](#features)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Testing Credentials](#testing-credentials)

---

## 🏗️ Project Architecture

This project follows a clean separation between frontend and backend:

```
bangla-learning-lms/
├── backend/                    # Spring Boot Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/bangla/lms/
│   │   │   │   ├── config/           # Security & JWT Configuration
│   │   │   │   ├── controller/       # REST Controllers
│   │   │   │   ├── dto/              # Data Transfer Objects
│   │   │   │   ├── entity/           # JPA Entities
│   │   │   │   ├── exception/        # Custom Exceptions
│   │   │   │   ├── repository/       # JPA Repositories
│   │   │   │   └── service/          # Business Logic
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── db/changelog/     # Liquibase Migrations
│   │   └── test/
│   └── pom.xml
├── src/                        # React Frontend (Root Level)
│   ├── components/             # React Components
│   ├── services/               # API Services & Mock Data
│   └── main.tsx
├── index.html
├── package.json
└── vite.config.ts
```

---

## 🛠️ Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **JWT Authentication** (jjwt 0.11.5)
- **H2 Database** (In-Memory)
- **Liquibase** (Database Migrations)
- **Lombok** (Boilerplate Code Reduction)
- **Maven** (Dependency Management)

### Frontend
- **React 18**
- **TypeScript**
- **Vite** (Build Tool)
- **Tailwind CSS** (Styling)
- **Lucide React** (Icons)
- **React Router** (Navigation)

---

## ✅ Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher ([Download](https://www.oracle.com/java/technologies/downloads/#java17))
- **Maven 3.6+** ([Download](https://maven.apache.org/download.cgi))
- **Node.js 18+** and **npm** ([Download](https://nodejs.org/))
- **Git** ([Download](https://git-scm.com/))

### Verify Installation

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Node version
node -v

# Check npm version
npm -v
```

---

## 🚀 Getting Started

### Clone the Repository

```bash
git clone <your-repository-url>
cd bangla-learning-lms
```

---

## 🔧 Backend Setup

### 1. Navigate to Backend Directory

```bash
cd backend
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The backend will start on **http://localhost:8080**

### 4. Access H2 Console (Optional)

- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:banglalmsdb`
- **Username:** `sa`
- **Password:** *(leave blank)*

### Backend Endpoints

Once running, the API will be available at:

- **Base URL:** http://localhost:8080
- **Auth Endpoints:**
  - `POST /api/auth/register` - Register new user
  - `POST /api/auth/login` - Login user
- **Course Endpoints:**
  - `GET /api/courses` - Get all courses
  - `GET /api/courses/{id}` - Get course by ID
- **Lesson Endpoints:**
  - `GET /api/lessons/course/{courseId}` - Get lessons for a course
  - `GET /api/lessons/{id}` - Get lesson by ID

---

## 💻 Frontend Setup

### 1. Navigate to Project Root

```bash
cd ..  # If you're in the backend directory
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Run Development Server

```bash
npm run dev
```

The frontend will start on **http://localhost:5173**

### 4. Build for Production

```bash
npm run build
```

### 5. Preview Production Build

```bash
npm run preview
```

---

## ✨ Features

### Authentication System
- ✅ User Registration with validation
- ✅ User Login with JWT token generation
- ✅ Password encryption using BCrypt
- ✅ Protected routes with JWT authentication
- ✅ Strict password validation (8-12 chars, uppercase, lowercase, number, special char)

### Course Management
- ✅ Browse available Bangla courses
- ✅ View course details and descriptions
- ✅ Three difficulty levels: Beginner, Intermediate, Advanced

### Lesson Player
- ✅ YouTube video integration
- ✅ Markdown content rendering
- ✅ Lesson navigation sidebar
- ✅ Sequential lesson progression

### User Interface
- ✅ Clean, modern design with Tailwind CSS
- ✅ Responsive layout for all devices
- ✅ Bilingual interface (English & Bengali)
- ✅ Smooth animations and transitions

---

## 📚 API Documentation

### Authentication Endpoints

#### Register User

```http
POST /api/auth/register
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "Test@123",
  "phone": "+8801234567890",
  "gender": "male"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "STUDENT"
}
```

#### Login User

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "Test@123"
}
```

**Response:** Same as registration response

### Course Endpoints

#### Get All Courses

```http
GET /api/courses
```

#### Get Course by ID

```http
GET /api/courses/{id}
```

### Lesson Endpoints

#### Get Lessons by Course ID

```http
GET /api/lessons/course/{courseId}
Authorization: Bearer {token}
```

#### Get Lesson by ID

```http
GET /api/lessons/{id}
Authorization: Bearer {token}
```

---

## 🗄️ Database Schema

### Users Table
```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  role VARCHAR(20) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);
```

### Courses Table
```sql
CREATE TABLE courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  level VARCHAR(50) NOT NULL,
  thumbnail_url VARCHAR(500),
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);
```

### Lessons Table
```sql
CREATE TABLE lessons (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  title VARCHAR(255) NOT NULL,
  video_id VARCHAR(50) NOT NULL,
  content TEXT,
  order_index INT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  FOREIGN KEY (course_id) REFERENCES courses(id)
);
```

---

## 🔒 Security

### JWT Authentication

The application uses JSON Web Tokens (JWT) for stateless authentication:

- **Token Expiration:** 24 hours (86400000 ms)
- **Secret Key:** Configured in `application.properties`
- **Algorithm:** HS256

### Password Requirements

Passwords must meet the following criteria:
- Length: 8-12 characters
- At least 1 uppercase letter (A-Z)
- At least 1 lowercase letter (a-z)
- At least 1 number (0-9)
- At least 1 special character (@$!%*?&)

### CORS Configuration

The backend is configured to accept requests from:
- http://localhost:5173 (Vite dev server)
- http://localhost:3000 (Alternative dev server)

---

## 🔑 Testing Credentials

### Pre-loaded Users

The application comes with two pre-loaded test accounts:

#### Admin Account
- **Email:** admin@banglalms.com
- **Password:** Test@123
- **Role:** ADMIN

#### Student Account
- **Email:** student@banglalms.com
- **Password:** Test@123
- **Role:** STUDENT

### Sample Data

The database is pre-populated with:
- **2 Courses:**
  1. বাংলা বর্ণমালা - Bangla Alphabet (Beginner)
  2. প্রাথমিক কথোপকথন - Basic Conversations (Beginner)

- **7 Lessons** across the two courses

---

## 🐛 Troubleshooting

### Backend Issues

**Port 8080 Already in Use**
```bash
# Find process using port 8080
lsof -i :8080  # Mac/Linux
netstat -ano | findstr :8080  # Windows

# Kill the process or change the port in application.properties
server.port=8081
```

**Liquibase Errors**
```bash
# Clean rebuild
mvn clean install -DskipTests
```

### Frontend Issues

**Port 5173 Already in Use**
```bash
# Vite will automatically try the next available port
# Or specify a different port in vite.config.ts
```

**Module Not Found Errors**
```bash
# Clear cache and reinstall
rm -rf node_modules package-lock.json
npm install
```

---

## 📖 Additional Resources

### Spring Boot Documentation
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Liquibase](https://www.liquibase.org/get-started/quickstart)

### React Documentation
- [React Docs](https://react.dev/)
- [Vite Guide](https://vitejs.dev/guide/)
- [Tailwind CSS](https://tailwindcss.com/docs)

---

## 📝 Notes for Novice Programmers

### Code Organization

1. **Backend Structure:**
   - `entity/` - Database tables (models)
   - `repository/` - Database queries
   - `service/` - Business logic
   - `controller/` - HTTP endpoints
   - `dto/` - Data sent between frontend and backend
   - `config/` - Security and app configuration
   - `exception/` - Error handling

2. **Frontend Structure:**
   - `components/` - Reusable UI components
   - `services/` - API calls and mock data
   - Each component handles its own logic

### Debugging Tips

1. **Backend Logs:**
   - Check console output for errors
   - SQL queries are shown in logs (spring.jpa.show-sql=true)

2. **Frontend Debugging:**
   - Use browser DevTools (F12)
   - Check Console tab for errors
   - Check Network tab for API calls

3. **Database Inspection:**
   - Use H2 Console at http://localhost:8080/h2-console
   - Run SQL queries to see data

### Making Changes

1. **Add New Field to User:**
   - Update `User.java` entity
   - Create Liquibase changeset in `db/changelog/`
   - Update `RegisterRequest.java` DTO
   - Restart backend

2. **Add New API Endpoint:**
   - Add method in `Controller`
   - Implement logic in `Service`
   - Test with Postman or frontend

3. **Add New Frontend Page:**
   - Create component in `components/`
   - Add route in `App.tsx`
   - Add navigation link

---

## 📄 License

This project is created for educational purposes.

---

## 🤝 Contributing

This is a learning project. Feel free to fork and experiment!

---

## 📧 Support

For issues and questions:
1. Check the Troubleshooting section
2. Review Spring Boot/React documentation
3. Examine console logs for error messages

---

**Happy Learning!** 🎓🇧🇩
