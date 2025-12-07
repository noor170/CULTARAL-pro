# 🇧🇩 Bangla Learning LMS

<div align="center">

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)
![React](https://img.shields.io/badge/React-18.3.1-blue.svg)
![TypeScript](https://img.shields.io/badge/TypeScript-5.5.3-blue.svg)

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
