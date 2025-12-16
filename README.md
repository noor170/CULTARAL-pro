# 🇧🇩 Bangla Learning LMS

<div align="center">

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)
![React](https://img.shields.io/badge/React-18.3.1-blue.svg)
![TypeScript](https://img.shields.io/badge/TypeScript-5.5.3-blue.svg)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED.svg?logo=docker)
[![Docker Hub](https://img.shields.io/badge/Docker%20Hub-noor25-2496ED.svg?logo=docker)](https://hub.docker.com/u/noor25)

A comprehensive Learning Management System (LMS) designed to help foreigners learn the Bengali (Bangla) language through structured courses, interactive lessons, and progress tracking.

[Features](#features) • [Tech Stack](#tech-stack) • [Installation](#installation) • [API Documentation](#api-documentation) • [Screenshots](#screenshots)

</div>

---

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Docker Deployment](#-docker-deployment)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Resources Used](#resources-used)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 About

Bangla Learning LMS is a full-stack web application that provides an interactive platform for learning the Bengali language. The system features a modern React frontend with a robust Java Spring Boot backend, offering courses from beginner to intermediate levels covering alphabet, conversation, and grammar.

**Target Audience:** Non-native speakers and foreigners who want to learn Bengali/Bangla language systematically.

---

## ✨ Features

### 🎓 Core Learning Features

- **Course Catalog**
  - Browse all available Bangla language courses
  - Visual course cards with thumbnails and descriptions
  - Difficulty level indicators (Beginner, Intermediate, Advanced)
  - Free and Paid course options

- **Interactive Course View**
  - Detailed course information page
  - Complete lesson listing with progress indicators
  - Enrollment system with access control
  - Lock/unlock mechanism for premium content

- **Lesson Player**
  - Embedded video player for lesson videos
  - Rich text content viewer with Bangla script support
  - Tabbed interface (Video + Notes)
  - Markdown rendering for formatted content
  - "Mark as Complete" functionality
  - Sequential lesson navigation

- **Progress Tracking**
  - Track completed lessons per course
  - Visual progress indicators (checkmarks)
  - Enrollment history persistence
  - Continue learning from where you left off

- **Filtering & Organization**
  - Filter courses by difficulty level
  - View only enrolled courses
  - Browse all courses
  - Organized course categories

### 📱 User Experience

- **Responsive Design**
  - Mobile-first approach
  - Tablet and desktop optimized
  - Fluid layouts and adaptive components

- **Modern UI/UX**
  - Clean and intuitive interface
  - Smooth transitions and animations
  - Professional color scheme (emerald/teal)
  - High contrast for readability

- **Internationalization**
  - Full UTF-8 support for Bangla characters
  - Bilingual content (Bangla + English)
  - Proper font rendering for Bengali script

---

## 🛠 Tech Stack

### Frontend Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **React** | 18.3.1 | Core UI framework |
| **TypeScript** | 5.5.3 | Type-safe JavaScript |
| **JavaScript** | ES6+ | Component logic |
| **Vite** | 5.4.2 | Build tool and dev server |
| **Tailwind CSS** | 3.4.1 | Utility-first CSS framework |
| **React Markdown** | 10.1.0 | Markdown content rendering |
| **Lucide React** | 0.344.0 | Modern icon library |

### Backend Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Primary programming language |
| **Spring Boot** | 3.2.0 | Application framework |
| **Spring Data JPA** | 3.2.0 | Database ORM |
| **Spring Web** | 3.2.0 | RESTful API |
| **H2 Database** | 2.x | In-memory database (dev) |
| **Lombok** | Latest | Reduce boilerplate code |
| **Maven** | 3.6+ | Build and dependency management |
| **Jakarta Persistence API** | 3.x | JPA specification |

### Development Tools

| Tool | Purpose |
|------|---------|
| **ESLint** | JavaScript/TypeScript linting |
| **PostCSS** | CSS processing |
| **Autoprefixer** | CSS vendor prefixes |
| **TypeScript ESLint** | TypeScript-specific linting |

---

## 📁 Project Structure

```
bangla-learning-lms/
│
├── frontend/                          # React Frontend Application
│   ├── src/
│   │   ├── components/               # React Components
│   │   │   ├── Navbar.jsx           # Navigation bar
│   │   │   ├── CourseList.jsx       # Course catalog grid
│   │   │   ├── CourseCard.jsx       # Individual course card
│   │   │   ├── CourseDetail.jsx     # Course detail page
│   │   │   └── LessonPlayer.jsx     # Lesson viewer component
│   │   │
│   │   ├── services/                # Services Layer
│   │   │   └── mockData.js          # Mock data and API simulation
│   │   │
│   │   ├── App.tsx                  # Root component
│   │   ├── main.tsx                 # Application entry point
│   │   ├── index.css                # Global styles
│   │   └── vite-env.d.ts            # Vite type definitions
│   │
│   ├── public/                       # Static assets
│   ├── index.html                    # HTML entry point
│   ├── package.json                  # Dependencies and scripts
│   ├── vite.config.ts               # Vite configuration
│   ├── tailwind.config.js           # Tailwind CSS config
│   ├── tsconfig.json                # TypeScript configuration
│   └── eslint.config.js             # ESLint configuration
│
├── backend/                          # Spring Boot Backend
│   ├── src/main/java/com/bangla/lms/
│   │   ├── entity/                  # JPA Entities
│   │   │   ├── Course.java         # Course entity
│   │   │   └── Lesson.java         # Lesson entity
│   │   │
│   │   ├── repository/              # Data Access Layer
│   │   │   ├── CourseRepository.java
│   │   │   └── LessonRepository.java
│   │   │
│   │   ├── service/                 # Business Logic Layer
│   │   │   ├── CourseService.java
│   │   │   └── LessonService.java
│   │   │
│   │   ├── controller/              # REST Controllers
│   │   │   ├── CourseController.java
│   │   │   └── LessonController.java
│   │   │
│   │   ├── dto/                     # Data Transfer Objects
│   │   │   ├── CourseDTO.java
│   │   │   └── LessonDTO.java
│   │   │
│   │   ├── config/                  # Configuration Classes
│   │   │   └── DataInitializer.java
│   │   │
│   │   └── BanglaLearningLmsApplication.java  # Main class
│   │
│   ├── src/main/resources/
│   │   └── application.properties   # Application configuration
│   │
│   ├── pom.xml                      # Maven dependencies
│   └── README.md                    # Backend documentation
│
├── README.md                         # Project documentation (this file)
├── INTEGRATION_GUIDE.md             # Frontend-Backend integration guide
└── .gitignore                       # Git ignore rules
```

---

## 🚀 Installation

### Prerequisites

**Frontend:**
- Node.js 18+ and npm/yarn
- Modern web browser

**Backend:**
- Java Development Kit (JDK) 17+
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Frontend Setup

```bash
# Clone the repository
git clone https://github.com/yourusername/bangla-learning-lms.git
cd bangla-learning-lms

# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build
```

The application will be available at `http://localhost:5173`

### Backend Setup

```bash
# Navigate to backend directory
cd backend

# Install dependencies and build
mvn clean install

# Run the application
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

Access H2 Database Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:banglalmsdb`
- Username: `sa`
- Password: (leave empty)

---

## 🐳 Docker Deployment

### Prerequisites

- **Docker:** Version 20.10 or higher
- **Docker Compose:** Version 2.0 or higher

Check if Docker is installed:
```bash
docker --version
docker-compose --version
```

### Quick Start with Docker Compose

The easiest way to run the entire application stack:

```bash
# Clone the repository
git clone https://github.com/yourusername/bangla-learning-lms.git
cd bangla-learning-lms

# Build and start all services
docker-compose up --build -d

# Check running containers
docker-compose ps

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

**Access the Application:**
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

### Docker Images

Pre-built Docker images are available on Docker Hub:

#### Backend Image
```bash
# Pull from Docker Hub
docker pull noor25/bangla-lms-backend:latest

# Run the backend container
docker run -d \
  --name bangla-backend \
  -p 8080:8080 \
  -v $(pwd)/data:/data \
  -e SPRING_DATASOURCE_URL=jdbc:h2:file:/data/lmsdb \
  noor25/bangla-lms-backend:latest
```

#### Frontend Image
```bash
# Pull from Docker Hub
docker pull noor25/bangla-lms-frontend:latest

# Run the frontend container
docker run -d \
  --name bangla-frontend \
  -p 3000:80 \
  noor25/bangla-lms-frontend:latest
```

### Building Docker Images Manually

#### Build Backend Image
```bash
# Navigate to backend directory
cd backend

# Build the image
docker build -t bangla-lms-backend:latest .

# Run the container
docker run -d -p 8080:8080 --name backend bangla-lms-backend:latest
```

#### Build Frontend Image
```bash
# From project root directory
docker build -t bangla-lms-frontend:latest .

# Run the container
docker run -d -p 3000:80 --name frontend bangla-lms-frontend:latest
```

### Docker Compose Configuration

The `docker-compose.yml` file orchestrates both services:

```yaml
services:
  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    container_name: bangla-lms-backend
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:h2:file:/data/lmsdb
    volumes:
      - ./data:/data
    restart: unless-stopped

  frontend:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: bangla-lms-frontend
    ports:
      - "3000:80"
    depends_on:
      backend:
        condition: service_healthy
    restart: unless-stopped
```

### Docker Commands Reference

#### Container Management
```bash
# List all containers
docker ps -a

# Start stopped containers
docker start bangla-lms-backend bangla-lms-frontend

# Stop running containers
docker stop bangla-lms-backend bangla-lms-frontend

# Restart containers
docker restart bangla-lms-backend bangla-lms-frontend

# Remove containers
docker rm bangla-lms-backend bangla-lms-frontend

# Remove containers forcefully
docker rm -f bangla-lms-backend bangla-lms-frontend
```

#### Image Management
```bash
# List all images
docker images

# Remove an image
docker rmi bangla-lms-backend:latest

# Remove all unused images
docker image prune -a

# View image details
docker inspect bangla-lms-backend:latest
```

#### Logs and Debugging
```bash
# View container logs
docker logs bangla-lms-backend
docker logs bangla-lms-frontend

# Follow logs in real-time
docker logs -f bangla-lms-backend

# View last 100 lines of logs
docker logs --tail 100 bangla-lms-backend

# Execute commands inside container
docker exec -it bangla-lms-backend bash
docker exec -it bangla-lms-frontend sh

# Check container resource usage
docker stats
```

#### Docker Compose Commands
```bash
# Build images without starting containers
docker-compose build

# Start services in background
docker-compose up -d

# Start services with rebuild
docker-compose up --build

# Stop services (keeps containers)
docker-compose stop

# Stop and remove containers, networks, volumes
docker-compose down

# Stop and remove everything including volumes
docker-compose down -v

# View service status
docker-compose ps

# View logs for all services
docker-compose logs

# View logs for specific service
docker-compose logs backend
docker-compose logs frontend

# Scale services (if needed)
docker-compose up -d --scale backend=2

# Restart specific service
docker-compose restart backend
```

### Pushing Images to Docker Hub

#### 1. Login to Docker Hub
```bash
docker login
# Enter your Docker Hub username and password
```

#### 2. Tag Your Images
```bash
# Tag backend image
docker tag bangla-lms-backend:latest YOUR_USERNAME/bangla-lms-backend:latest

# Tag frontend image
docker tag bangla-lms-frontend:latest YOUR_USERNAME/bangla-lms-frontend:latest

# Tag with version number
docker tag bangla-lms-backend:latest YOUR_USERNAME/bangla-lms-backend:v1.0.0
```

#### 3. Push Images to Docker Hub
```bash
# Push backend image
docker push YOUR_USERNAME/bangla-lms-backend:latest

# Push frontend image
docker push YOUR_USERNAME/bangla-lms-frontend:latest

# Push specific version
docker push YOUR_USERNAME/bangla-lms-backend:v1.0.0
```

#### 4. Pull and Use Your Images
```bash
# Pull your images from Docker Hub
docker pull YOUR_USERNAME/bangla-lms-backend:latest
docker pull YOUR_USERNAME/bangla-lms-frontend:latest

# Run containers from Docker Hub images
docker run -d -p 8080:8080 YOUR_USERNAME/bangla-lms-backend:latest
docker run -d -p 3000:80 YOUR_USERNAME/bangla-lms-frontend:latest
```

### Production Docker Deployment

#### Using Docker Hub Images

Create a `docker-compose.prod.yml` file:

```yaml
services:
  backend:
    image: noor25/bangla-lms-backend:latest
    container_name: bangla-lms-backend-prod
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/banglalms
      - SPRING_DATASOURCE_USERNAME=${DB_USER}
      - SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}
    restart: always
    networks:
      - app-network

  frontend:
    image: noor25/bangla-lms-frontend:latest
    container_name: bangla-lms-frontend-prod
    ports:
      - "80:80"
      - "443:443"
    restart: always
    networks:
      - app-network
    depends_on:
      - backend

networks:
  app-network:
    driver: bridge
```

Run in production:
```bash
docker-compose -f docker-compose.prod.yml up -d
```

### Environment Variables

#### Backend Environment Variables
```bash
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:h2:file:/data/lmsdb
SPRING_DATASOURCE_USERNAME=sa
SPRING_DATASOURCE_PASSWORD=

# Server Configuration
SERVER_PORT=8080

# H2 Console
SPRING_H2_CONSOLE_ENABLED=true
SPRING_H2_CONSOLE_PATH=/h2-console

# JPA Configuration
SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
```

#### Frontend Environment Variables
```bash
# API Base URL (if using environment-specific configs)
VITE_API_BASE_URL=http://localhost:8080/api
```

### Docker Volumes

Persist data across container restarts:

```bash
# Create named volume
docker volume create bangla-lms-data

# Run backend with named volume
docker run -d \
  --name bangla-backend \
  -p 8080:8080 \
  -v bangla-lms-data:/data \
  noor25/bangla-lms-backend:latest

# List volumes
docker volume ls

# Inspect volume
docker volume inspect bangla-lms-data

# Remove volume
docker volume rm bangla-lms-data
```

### Docker Networks

Create custom networks for container communication:

```bash
# Create custom network
docker network create bangla-lms-network

# Run containers on custom network
docker run -d \
  --name bangla-backend \
  --network bangla-lms-network \
  -p 8080:8080 \
  noor25/bangla-lms-backend:latest

docker run -d \
  --name bangla-frontend \
  --network bangla-lms-network \
  -p 3000:80 \
  noor25/bangla-lms-frontend:latest

# List networks
docker network ls

# Inspect network
docker network inspect bangla-lms-network

# Remove network
docker network rm bangla-lms-network
```

### Troubleshooting

#### Common Issues

**1. Port Already in Use**
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process or use different port
docker run -d -p 9090:8080 noor25/bangla-lms-backend:latest
```

**2. Container Keeps Restarting**
```bash
# Check container logs
docker logs bangla-lms-backend

# Check container status
docker inspect bangla-lms-backend
```

**3. Cannot Connect to Backend from Frontend**
```bash
# Ensure containers are on same network
docker network inspect bridge

# Use container name or IP for communication
# Update frontend API URL to: http://bangla-lms-backend:8080/api
```

**4. Build Failures**
```bash
# Clean up and rebuild
docker-compose down -v
docker system prune -a
docker-compose up --build
```

**5. Out of Disk Space**
```bash
# Remove unused containers, images, networks
docker system prune -a

# Remove specific images
docker image prune -a

# Check disk usage
docker system df
```

### Docker Health Checks

Monitor container health:

```bash
# Check health status
docker ps --filter "health=healthy"

# View health check logs
docker inspect --format='{{json .State.Health}}' bangla-lms-backend | jq
```

### Performance Optimization

#### Resource Limits
```bash
# Run container with memory limit
docker run -d \
  --name bangla-backend \
  --memory="512m" \
  --cpus="1.0" \
  -p 8080:8080 \
  noor25/bangla-lms-backend:latest
```

#### Multi-stage Builds
Both Dockerfiles use multi-stage builds to reduce image size:
- Backend: 503MB (Maven build + JRE runtime)
- Frontend: 76.4MB (Node build + Nginx)

### Docker Hub Repository

Official Docker Hub repositories:
- **Backend:** [noor25/bangla-lms-backend](https://hub.docker.com/r/noor25/bangla-lms-backend)
- **Frontend:** [noor25/bangla-lms-frontend](https://hub.docker.com/r/noor25/bangla-lms-frontend)

#### Repository Commands
```bash
# Search for images
docker search noor25/bangla-lms

# Pull specific version
docker pull noor25/bangla-lms-backend:v1.0.0

# View image layers
docker history noor25/bangla-lms-backend:latest
```

---

## 💻 Usage

### Running the Application

1. **Start Backend (Optional for MVP):**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

2. **Start Frontend:**
   ```bash
   npm run dev
   ```

3. **Access Application:**
   - Open browser to `http://localhost:5173`
   - Browse course catalog
   - Click on any course to view details
   - Click "Start Learning" to enroll
   - Access lessons and track progress

### Key User Flows

**1. Browse Courses**
- View all available courses on the home page
- Use filters to narrow down by level or enrollment status

**2. Enroll in Course**
- Click on a course card
- Review course details and lessons
- Click "Enroll Now" button
- Access unlocked content

**3. Learn Lessons**
- Select a lesson from the sidebar
- Watch video content
- Read lesson notes in Bangla
- Mark lesson as complete
- Navigate to next lesson

---

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Course Endpoints

#### Get All Courses
```http
GET /api/courses
```
**Response:** Array of CourseDTO objects

#### Get Course by ID
```http
GET /api/courses/{id}
```
**Parameters:**
- `id` (Long) - Course ID

**Response:** CourseDTO object

#### Get Courses by Level
```http
GET /api/courses/level/{level}
```
**Parameters:**
- `level` (String) - "Beginner", "Intermediate", or "Advanced"

**Response:** Array of CourseDTO objects

#### Create Course
```http
POST /api/courses
Content-Type: application/json
```
**Request Body:**
```json
{
  "title": "Course Title",
  "description": "Course description",
  "level": "Beginner",
  "thumbnailUrl": "https://example.com/image.jpg",
  "price": "Free"
}
```

#### Update Course
```http
PUT /api/courses/{id}
Content-Type: application/json
```

#### Delete Course
```http
DELETE /api/courses/{id}
```

### Lesson Endpoints

#### Get Lessons by Course ID
```http
GET /api/lessons/course/{courseId}
```
**Response:** Array of LessonDTO objects

#### Get Lesson by ID
```http
GET /api/lessons/{id}
```

#### Create Lesson
```http
POST /api/lessons/course/{courseId}
Content-Type: application/json
```
**Request Body:**
```json
{
  "title": "Lesson Title",
  "videoUrl": "https://youtube.com/embed/...",
  "content": "Lesson content in Bangla",
  "lessonOrder": 1
}
```

#### Update Lesson
```http
PUT /api/lessons/{id}
```

#### Delete Lesson
```http
DELETE /api/lessons/{id}
```

### Response Format

**CourseDTO:**
```json
{
  "id": 1,
  "title": "বাংলা বর্ণমালা - Bangla Alphabet",
  "description": "Learn the foundation...",
  "level": "Beginner",
  "thumbnailUrl": "https://...",
  "price": "Free",
  "lessonCount": 5
}
```

**LessonDTO:**
```json
{
  "id": 1,
  "courseId": 1,
  "title": "স্বরবর্ণ - Vowels",
  "videoUrl": "https://youtube.com/embed/...",
  "content": "বাংলা content...",
  "lessonOrder": 1
}
```

---

## 🗄 Database Schema

### Course Table
```sql
CREATE TABLE courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(500) NOT NULL,
  description VARCHAR(2000),
  level VARCHAR(50) NOT NULL,
  thumbnail_url VARCHAR(1000),
  price VARCHAR(20)
);
```

### Lesson Table
```sql
CREATE TABLE lessons (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  title VARCHAR(500) NOT NULL,
  video_url VARCHAR(1000),
  content TEXT,
  lesson_order INTEGER,
  FOREIGN KEY (course_id) REFERENCES courses(id)
);
```

### Entity Relationships
- One Course has Many Lessons (One-to-Many)
- Cascade delete: Deleting a course deletes all its lessons
- UTF-8 encoding for all text fields

---

## 📚 Resources Used

### Content Resources

**Sample Courses:**
1. **বাংলা বর্ণমালা (Bangla Alphabet)**
   - 5 lessons covering vowels, consonants, and compound letters
   - Beginner level, Free

2. **প্রাথমিক কথোপকথন (Basic Conversations)**
   - 5 lessons on greetings, introductions, numbers, daily phrases
   - Beginner level, Free

3. **বাংলা ব্যাকরণ (Bangla Grammar)**
   - 5 lessons on nouns, pronouns, verbs, adjectives, sentence structure
   - Intermediate level, Paid

### Image Resources

**Stock Photos from Pexels:**
- Course thumbnails sourced from [Pexels](https://www.pexels.com)
- Free to use under Pexels License
- Images selected for educational context

### Fonts & Icons

- **Icons:** [Lucide Icons](https://lucide.dev) - Open source icon library
- **System Fonts:** Default system font stack for optimal Bengali rendering
  - macOS/iOS: SF Pro
  - Windows: Segoe UI
  - Linux: System default

### Learning Content

- **Bangla Alphabet:** Traditional Bengali vowels (স্বরবর্ণ) and consonants (ব্যঞ্জনবর্ণ)
- **Pronunciation Guides:** Romanization for each Bangla word
- **Example Sentences:** Real-world usage examples
- **Grammar Rules:** Traditional Bengali grammar structure

### Third-Party Libraries

**Frontend:**
```json
{
  "@supabase/supabase-js": "^2.57.4",
  "lucide-react": "^0.344.0",
  "react": "^18.3.1",
  "react-dom": "^18.3.1",
  "react-markdown": "^10.1.0"
}
```

**Backend:**
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
  </dependency>
</dependencies>
```

### Development Resources

- **Color Palette:** Emerald and Teal gradients
  - Primary: `#059669` (Emerald 600)
  - Secondary: `#0d9488` (Teal 600)
- **Spacing System:** 8px base unit (Tailwind default)
- **Typography:** Default Tailwind type scale

---

## 🎨 Design System

### Colors
```css
Primary: Emerald (#059669)
Secondary: Teal (#0d9488)
Success: Green (#16a34a)
Warning: Yellow (#ca8a04)
Error: Red (#dc2626)
Neutral: Gray scale
```

### Typography
- Headings: Bold, large sizes
- Body: Regular weight, comfortable reading size
- Bangla Text: Larger size for better readability

### Components
- Cards with hover effects
- Buttons with transitions
- Progress indicators
- Responsive grid layouts

---

## 🧪 Testing

### Run Frontend Tests
```bash
npm run test
```

### Run Backend Tests
```bash
mvn test
```

### Type Checking
```bash
npm run typecheck
```

### Linting
```bash
npm run lint
```

---

## 📦 Building for Production

### Frontend
```bash
npm run build
```
Output: `dist/` directory

### Backend
```bash
mvn clean package
```
Output: `target/bangla-learning-lms-1.0.0.jar`

---

## 🚢 Deployment

### Frontend Deployment Options
- **Vercel:** Connect GitHub repo for automatic deployments
- **Netlify:** Drag-and-drop `dist` folder
- **AWS S3 + CloudFront:** Static website hosting
- **GitHub Pages:** Free hosting for static sites

### Backend Deployment Options
- **Heroku:** Java buildpack support
- **Railway:** Container-based deployment
- **AWS Elastic Beanstalk:** Managed Java environment
- **DigitalOcean App Platform:** Simple app deployment

### Production Considerations
- Replace H2 with PostgreSQL or MySQL
- Configure environment variables
- Set up proper CORS policies
- Enable HTTPS
- Implement authentication
- Set up monitoring and logging

---

## 🔮 Future Enhancements

- [ ] User authentication and authorization (JWT)
- [ ] Payment integration (Stripe/PayPal)
- [ ] Quiz and assessment system
- [ ] Certificate generation
- [ ] Discussion forums per course
- [ ] Live classes integration
- [ ] Mobile app (React Native)
- [ ] Progress analytics dashboard
- [ ] Gamification (badges, points)
- [ ] Social learning features
- [ ] AI-powered pronunciation feedback
- [ ] Offline mode support

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards
- Follow existing code style
- Write meaningful commit messages
- Add comments for complex logic
- Update documentation as needed
- Write tests for new features

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

- **Your Name** - Initial work - [YourGitHub](https://github.com/yourusername)

---

## 🙏 Acknowledgments

- Bengali language resources and learning materials
- Pexels for free stock photography
- Lucide for beautiful icons
- Spring Boot and React communities
- All contributors and supporters

---

## 📞 Contact & Support

- **Project Link:** [https://github.com/yourusername/bangla-learning-lms](https://github.com/yourusername/bangla-learning-lms)
- **Issues:** [GitHub Issues](https://github.com/yourusername/bangla-learning-lms/issues)
- **Email:** your.email@example.com

---

## 📊 Project Statistics

- **Total Courses:** 3 (MVP)
- **Total Lessons:** 15 (5 per course)
- **Supported Levels:** Beginner, Intermediate
- **Languages:** Bengali (বাংলা), English
- **Lines of Code:** ~5,000+

---

<div align="center">

**Made with ❤️ for Bengali Language Learners**

[![GitHub stars](https://img.shields.io/github/stars/yourusername/bangla-learning-lms?style=social)](https://github.com/yourusername/bangla-learning-lms/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/yourusername/bangla-learning-lms?style=social)](https://github.com/yourusername/bangla-learning-lms/network/members)

</div>
