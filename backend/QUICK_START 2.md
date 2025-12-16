# Bangla Learning LMS - Backend Quick Start Guide

## 🚀 Quick Start

This guide will help you get the Spring Boot backend running in just a few minutes.

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Run the Application

```bash
# Navigate to backend directory
cd backend

# Run with Maven
mvn spring-boot:run
```

That's it! The server will start on **http://localhost:8080**

## 🔍 Verify It's Working

### Check Server Status

Open your browser and visit:
- **http://localhost:8080/api/courses** - You should see JSON with 2 courses

### H2 Database Console

- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:banglalmsdb`
- **Username:** `sa`
- **Password:** *(leave blank)*

## 🧪 Test the API

### Using cURL

**Get All Courses:**
```bash
curl http://localhost:8080/api/courses
```

**Register a New User:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "password": "Test@123",
    "phone": "+8801234567890",
    "gender": "male"
  }'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "Test@123"
  }'
```

### Using Postman

1. Import the following endpoints:
   - `GET http://localhost:8080/api/courses`
   - `POST http://localhost:8080/api/auth/register`
   - `POST http://localhost:8080/api/auth/login`

2. For protected endpoints, add header:
   ```
   Authorization: Bearer <your-jwt-token>
   ```

## 🔑 Test Credentials

Two users are pre-loaded in the database:

**Admin:**
- Email: `admin@banglalms.com`
- Password: `Test@123`

**Student:**
- Email: `student@banglalms.com`
- Password: `Test@123`

## 📚 Available Endpoints

### Public Endpoints (No Authentication Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login user |
| GET | `/api/courses` | Get all courses |
| GET | `/api/courses/{id}` | Get course by ID |

### Protected Endpoints (Require JWT Token)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/lessons/course/{courseId}` | Get lessons by course |
| GET | `/api/lessons/{id}` | Get lesson by ID |
| POST | `/api/courses` | Create new course (Admin only) |
| PUT | `/api/courses/{id}` | Update course (Admin only) |
| DELETE | `/api/courses/{id}` | Delete course (Admin only) |

## 🔧 Configuration

All configuration is in `src/main/resources/application.properties`:

```properties
# Server Port
server.port=8080

# Database (H2 In-Memory)
spring.datasource.url=jdbc:h2:mem:banglalmsdb
spring.datasource.username=sa
spring.datasource.password=

# JWT Configuration
jwt.secret=BanglaLearningLMS2024SecretKeyForJWTAuthenticationMinimum256BitsRequired
jwt.expiration=86400000
```

## 🛠️ Build & Package

**Create Executable JAR:**
```bash
mvn clean package
```

**Run the JAR:**
```bash
java -jar target/bangla-learning-lms-1.0.0.jar
```

## 🐛 Common Issues

### Port Already in Use

**Problem:** `Port 8080 is already in use`

**Solution:**
```properties
# Change port in application.properties
server.port=8081
```

### Liquibase Errors

**Problem:** Database migration errors

**Solution:**
```bash
mvn clean install
```

### Java Version Issues

**Problem:** `Unsupported class version`

**Solution:** Ensure you're using Java 17+
```bash
java -version
```

## 📖 Project Structure

```
backend/
├── src/main/java/com/bangla/lms/
│   ├── config/              # Security & JWT
│   ├── controller/          # REST Endpoints
│   ├── dto/                 # Request/Response objects
│   ├── entity/              # Database models
│   ├── exception/           # Error handling
│   ├── repository/          # Database queries
│   └── service/             # Business logic
├── src/main/resources/
│   ├── application.properties
│   └── db/changelog/        # Database migrations
└── pom.xml                  # Dependencies
```

## 🔐 Security Notes

- Passwords are encrypted with BCrypt
- JWT tokens expire after 24 hours
- CORS is enabled for localhost:5173 and localhost:3000
- H2 Console is accessible (disable in production!)

## 📝 Next Steps

1. ✅ Backend is running
2. 🔜 Start the frontend: `cd .. && npm run dev`
3. 🔜 Test login/registration from UI
4. 🔜 Browse courses and lessons

## 📚 For More Details

See the main project README: `../README_FULLSTACK.md`

---

**Need Help?** Check the logs in the console for detailed error messages.
