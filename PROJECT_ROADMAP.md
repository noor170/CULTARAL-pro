# 🚀 CULTARAL-PRO: Complete Setup & Development Guide

## ❌ Current Issue: Docker Daemon Not Running

You're getting this error:
```
Cannot connect to the Docker daemon at unix:///Users/macbookairm1/.docker/run/docker.sock
```

### ✅ Fix for macOS:

**Option 1: Start Docker Desktop (Easiest)**
1. Open **Applications** → **Docker.app**
2. Wait for Docker icon to appear in the menu bar
3. Try again: `docker-compose up --build`

**Option 2: Fix docker-compose.yml version warning**
```bash
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro
# Remove the obsolete 'version' line from docker-compose.yml
```

---

## 📊 Project Structure Overview

### Backend (Java Spring Boot 3.2.0)
```
backend/
├── pom.xml                          # Maven configuration
├── src/main/java/com/bangla/lms/
│   ├── BanglaLearningLmsApplication.java     # Main entry point
│   ├── config/                      # Configuration classes
│   │   ├── JwtProperties.java       # JWT configuration (@ConfigurationProperties)
│   │   ├── JwtUtil.java             # JWT token generation/validation
│   │   ├── JwtAuthenticationFilter.java  # JWT request filter
│   │   ├── SecurityConfig.java      # Spring Security setup
│   │   └── ApplicationConfig.java   # Application beans
│   ├── controller/                  # REST endpoints
│   ├── service/                     # Business logic
│   ├── repository/                  # JPA repositories
│   ├── entity/                      # Database entities
│   ├── dto/                         # Data transfer objects
│   └── exception/                   # Exception handlers
├── src/main/resources/
│   ├── application.yml              # Spring Boot configuration
│   ├── db/changelog/                # Liquibase migration scripts
│   │   ├── 01-init-schema.yaml      # Create tables
│   │   ├── 02-insert-data.yaml      # Sample data
│   │   └── 03-make-videoid-nullable.yaml  # Column modification
│   └── db.changelog-master.yaml     # Migration master file
├── Dockerfile                       # Docker image definition
└── .dockerignore

### Frontend (React + Vite)
```
src/
├── main.tsx                         # React entry point
├── App.tsx                          # Main component
├── index.css                        # Global styles
├── components/
│   ├── CourseCard.jsx               # Course display card
│   ├── CourseDetail.jsx             # Course details page
│   ├── CourseList.jsx               # Courses listing
│   ├── LessonPlayer.jsx             # Video lesson player
│   ├── Login.jsx                    # Login form
│   ├── Register.jsx                 # Registration form
│   └── Navbar.jsx                   # Navigation bar
└── services/
    └── mockData.js                  # Mock API data

vite.config.ts                       # Vite build config
tailwind.config.js                   # Tailwind CSS config
postcss.config.js                    # PostCSS config
tsconfig.json                        # TypeScript config
Dockerfile                           # Docker image definition
nginx.conf                           # Nginx proxy config
```

---

## 🎯 What You Can Do With This Project

### ✅ Already Implemented (Backend)
- ✅ **JWT Authentication** - Secure token-based auth
- ✅ **Spring Security 6** - Modern security configuration
- ✅ **JPA Repositories** - Database ORM with H2/can migrate to PostgreSQL
- ✅ **Liquibase Migrations** - Database version control
- ✅ **RESTful APIs** - Courses, Lessons, Authentication endpoints
- ✅ **Role-based Access** - ADMIN, STUDENT, INSTRUCTOR roles
- ✅ **Request/Response DTOs** - Proper API contracts

### ✅ Already Implemented (Frontend)
- ✅ **React 18** - Modern UI library
- ✅ **Vite** - Fast build tool
- ✅ **React Router** - Client-side routing
- ✅ **Tailwind CSS** - Utility-first styling
- ✅ **Responsive Design** - Mobile-friendly layout

### 🔧 What Needs Completion

#### Backend Enhancements
1. **Complete REST Controllers**
   - [ ] `AuthController` - Login/Register endpoints (partial)
   - [ ] `CourseController` - CRUD operations for courses
   - [ ] `LessonController` - CRUD operations for lessons
   - [ ] `UserController` - User profile management
   - [ ] Add pagination & filtering

2. **Advanced Features**
   - [ ] Email notifications (course enrollment, password reset)
   - [ ] File uploads (course materials, profile pictures)
   - [ ] Student progress tracking
   - [ ] Ratings & reviews for courses
   - [ ] Search functionality

3. **Database Improvements**
   - [ ] Migrate from H2 to PostgreSQL for production
   - [ ] Add indexes for performance
   - [ ] Implement database backup strategy

4. **API Documentation**
   - [ ] Swagger/OpenAPI with Springdoc
   - [ ] API documentation endpoint

#### Frontend Enhancements
1. **Pages & Views**
   - [ ] Dashboard (student overview)
   - [ ] Course enrollment flow
   - [ ] Lesson progress tracker
   - [ ] Admin panel (manage courses/users)
   - [ ] User profile page

2. **State Management**
   - [ ] Redux or Zustand for global state
   - [ ] API client setup (axios/fetch with interceptors)
   - [ ] Authentication token storage (localStorage/sessionStorage)

3. **Features**
   - [ ] Video player integration (YouTube embed)
   - [ ] Progress tracking
   - [ ] Certificates upon completion
   - [ ] Discussion forum/comments
   - [ ] Notifications

4. **Styling & UX**
   - [ ] Complete responsive design
   - [ ] Dark mode support
   - [ ] Accessibility (WCAG compliance)
   - [ ] Loading states & error handling

#### Testing
- [ ] Unit tests (JUnit 5, Jest)
- [ ] Integration tests
- [ ] E2E tests (Cypress/Playwright)

---

## 🚀 Recommended Development Workflow

### Phase 1: Local Development (Current)
```bash
# Terminal 1: Start Backend
cd backend
mvn spring-boot:run
# http://localhost:8080

# Terminal 2: Start Frontend
npm run dev
# http://localhost:5173
```

### Phase 2: Docker Development
```bash
# Fix docker-compose.yml and start both services
docker-compose up --build
# Frontend: http://localhost:3000
# Backend: http://localhost:8080
```

### Phase 3: Production Deployment
```bash
# Use docker-compose with PostgreSQL
# Add environment secrets management
# Deploy to cloud (AWS, GCP, Azure, DigitalOcean)
```

---

## 📋 Next Steps - Immediate Actions

### 1. **Start Docker Desktop**
   - macOS: Open Applications → Docker.app
   - Windows: Open Docker Desktop
   - Linux: `sudo systemctl start docker`

### 2. **Fix docker-compose.yml**
```bash
# Remove the 'version: 3.8' line (it's obsolete)
# Modern Docker Compose doesn't need it
```

### 3. **Remove node_modules & reinstall** (if needed)
```bash
npm install
npm run build
```

### 4. **Test Backend**
```bash
cd backend
mvn clean spring-boot:run
# Should see: "Tomcat started on port(s): 8080"
```

### 5. **Test Frontend**
```bash
npm run dev
# Should see: "Local: http://localhost:5173"
```

### 6. **Test Docker**
```bash
docker-compose up --build
# Access: http://localhost:3000 (frontend)
#         http://localhost:8080 (backend)
```

---

## 🔗 API Endpoints (Backend)

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login with credentials
- `POST /api/auth/logout` - Logout (if implemented)

### Courses
- `GET /api/courses` - List all courses
- `GET /api/courses/{id}` - Get course details
- `POST /api/courses` - Create course (ADMIN only)
- `PUT /api/courses/{id}` - Update course
- `DELETE /api/courses/{id}` - Delete course

### Lessons
- `GET /api/lessons?courseId={id}` - Get lessons by course
- `GET /api/lessons/{id}` - Get lesson details
- `POST /api/lessons` - Create lesson (ADMIN only)
- `PUT /api/lessons/{id}` - Update lesson
- `DELETE /api/lessons/{id}` - Delete lesson

### Database Console
- `GET /h2-console` - H2 database admin (development only)

---

## 🛠️ Technology Stack Summary

| Layer | Technology | Version |
|-------|-----------|---------|
| **Frontend** | React | 18.x |
| **Build Tool** | Vite | Latest |
| **Styling** | Tailwind CSS | 3.x |
| **Backend** | Spring Boot | 3.2.0 |
| **Java** | OpenJDK | 17+ |
| **Security** | Spring Security | 6.x |
| **Auth** | JWT (JJWT) | 0.11.5 |
| **ORM** | Spring Data JPA | 3.2.0 |
| **Database** | H2 (dev), PostgreSQL (prod) | Latest |
| **Migrations** | Liquibase | Latest |
| **Containerization** | Docker & Docker Compose | Latest |
| **Web Server** | Nginx (prod) | Latest |

---

## 💡 Tips for Success

✅ **Do:**
- Use feature branches for new development
- Write tests before implementing features
- Document APIs as you build
- Test locally before Docker deployment
- Use environment variables for secrets

❌ **Don't:**
- Commit `.env` files or secrets
- Use H2 database in production
- Ignore CORS errors (fix properly in config)
- Skip database migrations
- Deploy without testing

---

## 📚 Resources

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [React Docs](https://react.dev)
- [Vite Docs](https://vitejs.dev)
- [Docker Compose Docs](https://docs.docker.com/compose/)
- [JWT.io](https://jwt.io)
- [Tailwind CSS](https://tailwindcss.com)

---

**Ready to start? Pick a task from the "What Needs Completion" section and create an issue/branch!** 🚀
