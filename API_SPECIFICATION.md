# 🔌 Backend API Specification - Implementation Guide

## Current Status
- ✅ Database schema defined (Liquibase)
- ✅ JPA entities created
- ✅ Repositories defined
- ⚠️ Controllers are stubs - **NEED IMPLEMENTATION**
- ⚠️ Services mostly incomplete - **NEED IMPLEMENTATION**

---

## 📋 REST API Endpoints to Implement

### 1. Authentication Endpoints
**Base Path:** `/api/auth`

#### Register User
```
POST /api/auth/register
Content-Type: application/json

Request:
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "SecurePass123!",
  "phone": "+8801234567890",
  "gender": "male"
}

Response (201 Created):
{
  "token": "eyJhbGc...",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "STUDENT"
}
```

#### Login
```
POST /api/auth/login
Content-Type: application/json

Request:
{
  "email": "john@example.com",
  "password": "SecurePass123!"
}

Response (200 OK):
{
  "token": "eyJhbGc...",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "STUDENT"
}
```

#### Logout (Optional)
```
POST /api/auth/logout
Authorization: Bearer {token}

Response (200 OK):
{
  "message": "Logged out successfully"
}
```

---

### 2. Course Endpoints
**Base Path:** `/api/courses`

#### Get All Courses (Public)
```
GET /api/courses
Query Parameters:
  - page: int (default: 0)
  - size: int (default: 10)
  - level: string (BEGINNER, INTERMEDIATE, ADVANCED)
  - search: string (search in title/description)

Response (200 OK):
{
  "content": [
    {
      "id": 1,
      "title": "বাংলা বর্ণমালা - Bangla Alphabet",
      "description": "Learn the foundation of Bangla language...",
      "level": "Beginner",
      "thumbnailUrl": "https://...",
      "price": 0.00,
      "createdAt": "2024-01-01T00:00:00",
      "updatedAt": "2024-01-01T00:00:00"
    }
  ],
  "totalElements": 10,
  "totalPages": 1,
  "currentPage": 0,
  "pageSize": 10
}
```

#### Get Single Course
```
GET /api/courses/{id}

Response (200 OK):
{
  "id": 1,
  "title": "বাংলা বর্ণমালা - Bangla Alphabet",
  "description": "...",
  "level": "Beginner",
  "thumbnailUrl": "https://...",
  "price": 0.00,
  "lessons": [
    {
      "id": 1,
      "title": "স্বরবর্ণ - Vowels",
      "videoId": "bUtVkW7pAcs",
      "content": "...",
      "orderIndex": 1
    }
  ],
  "createdAt": "2024-01-01T00:00:00",
  "updatedAt": "2024-01-01T00:00:00"
}
```

#### Create Course (ADMIN Only)
```
POST /api/courses
Authorization: Bearer {admin_token}
Content-Type: application/json

Request:
{
  "title": "Advanced Bangla Grammar",
  "description": "Learn advanced grammar rules...",
  "level": "ADVANCED",
  "thumbnailUrl": "https://...",
  "price": 49.99
}

Response (201 Created):
{
  "id": 2,
  "title": "Advanced Bangla Grammar",
  ...
}
```

#### Update Course (ADMIN Only)
```
PUT /api/courses/{id}
Authorization: Bearer {admin_token}
Content-Type: application/json

Request:
{
  "title": "Updated Title",
  "price": 29.99,
  ...
}

Response (200 OK):
{
  "id": 2,
  "title": "Updated Title",
  ...
}
```

#### Delete Course (ADMIN Only)
```
DELETE /api/courses/{id}
Authorization: Bearer {admin_token}

Response (204 No Content):
(empty body)
```

---

### 3. Lesson Endpoints
**Base Path:** `/api/lessons`

#### Get Lessons by Course
```
GET /api/lessons?courseId={courseId}
Query Parameters:
  - courseId: long (required)
  - page: int (default: 0)
  - size: int (default: 20)

Response (200 OK):
{
  "content": [
    {
      "id": 1,
      "courseId": 1,
      "title": "স্বরবর্ণ - Vowels (অ, আ, ই, ঈ)",
      "videoId": "bUtVkW7pAcs",
      "content": "# স্বরবর্ণ (Vowels)...",
      "orderIndex": 1,
      "createdAt": "2024-01-01T00:00:00"
    }
  ],
  "totalElements": 5,
  "totalPages": 1,
  "currentPage": 0,
  "pageSize": 20
}
```

#### Get Single Lesson
```
GET /api/lessons/{id}

Response (200 OK):
{
  "id": 1,
  "courseId": 1,
  "title": "স্বরবর্ণ - Vowels (অ, আ, ই, ঈ)",
  "videoId": "bUtVkW7pAcs",
  "content": "# স্বরবর্ণ (Vowels)...",
  "orderIndex": 1,
  "createdAt": "2024-01-01T00:00:00"
}
```

#### Create Lesson (ADMIN Only)
```
POST /api/lessons
Authorization: Bearer {admin_token}
Content-Type: application/json

Request:
{
  "courseId": 1,
  "title": "New Lesson Title",
  "videoId": "YouTube_Video_ID",
  "content": "Lesson markdown content...",
  "orderIndex": 6
}

Response (201 Created):
{
  "id": 6,
  "courseId": 1,
  ...
}
```

#### Update Lesson (ADMIN Only)
```
PUT /api/lessons/{id}
Authorization: Bearer {admin_token}
Content-Type: application/json

Request:
{
  "title": "Updated Lesson Title",
  "content": "Updated content...",
  ...
}

Response (200 OK):
{
  "id": 1,
  ...
}
```

#### Delete Lesson (ADMIN Only)
```
DELETE /api/lessons/{id}
Authorization: Bearer {admin_token}

Response (204 No Content):
(empty body)
```

---

### 4. User Endpoints (NEW - Not Yet Implemented)
**Base Path:** `/api/users`

#### Get Current User
```
GET /api/users/me
Authorization: Bearer {token}

Response (200 OK):
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "+8801234567890",
  "gender": "male",
  "role": "STUDENT",
  "createdAt": "2024-01-01T00:00:00"
}
```

#### Update User Profile
```
PUT /api/users/me
Authorization: Bearer {token}
Content-Type: application/json

Request:
{
  "firstName": "Jane",
  "lastName": "Smith",
  "phone": "+8801987654321"
}

Response (200 OK):
{
  "id": 1,
  "firstName": "Jane",
  ...
}
```

#### Change Password
```
POST /api/users/change-password
Authorization: Bearer {token}
Content-Type: application/json

Request:
{
  "oldPassword": "OldPass123!",
  "newPassword": "NewPass123!"
}

Response (200 OK):
{
  "message": "Password changed successfully"
}
```

---

### 5. Enrollment Endpoints (NEW - Not Yet Implemented)
**Base Path:** `/api/enrollments`

#### Enroll in Course
```
POST /api/enrollments
Authorization: Bearer {student_token}
Content-Type: application/json

Request:
{
  "courseId": 1
}

Response (201 Created):
{
  "id": 1,
  "studentId": 1,
  "courseId": 1,
  "enrolledAt": "2024-12-16T00:50:00",
  "completionPercentage": 0.0
}
```

#### Get My Enrollments
```
GET /api/enrollments/my-courses
Authorization: Bearer {student_token}

Response (200 OK):
{
  "content": [
    {
      "id": 1,
      "courseId": 1,
      "courseName": "বাংলা বর্ণমালা",
      "enrolledAt": "2024-12-16T00:50:00",
      "completionPercentage": 25.5
    }
  ]
}
```

#### Update Lesson Progress
```
PUT /api/enrollments/{enrollmentId}/progress
Authorization: Bearer {student_token}
Content-Type: application/json

Request:
{
  "lessonId": 1,
  "completed": true
}

Response (200 OK):
{
  "id": 1,
  "completionPercentage": 20.0
}
```

---

## 🔒 Authorization Rules

```
PUBLIC (No Token Needed):
- GET /api/courses
- GET /api/courses/{id}
- GET /api/lessons?courseId=...
- GET /api/lessons/{id}
- POST /api/auth/register
- POST /api/auth/login

STUDENT (Any Authenticated User):
- GET /api/users/me
- PUT /api/users/me
- POST /api/users/change-password
- POST /api/enrollments
- GET /api/enrollments/my-courses
- PUT /api/enrollments/{id}/progress

ADMIN ONLY:
- POST /api/courses
- PUT /api/courses/{id}
- DELETE /api/courses/{id}
- POST /api/lessons
- PUT /api/lessons/{id}
- DELETE /api/lessons/{id}
- GET /api/users (admin dashboard)
- DELETE /api/users/{id} (admin dashboard)
```

---

## 📝 Error Response Format

All errors should follow this format:

```json
{
  "timestamp": "2024-12-16T00:50:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "details": {
    "email": "Email is already registered",
    "password": "Password must be at least 8 characters"
  },
  "path": "/api/auth/register"
}
```

---

## 🎯 Implementation Priority

### Priority 1 (Essential MVP)
- [x] User entities & repositories
- [ ] AuthController (login/register)
- [ ] CourseController (GET operations)
- [ ] LessonController (GET operations)

### Priority 2 (Core Features)
- [ ] CourseController (POST/PUT/DELETE)
- [ ] LessonController (POST/PUT/DELETE)
- [ ] UserController
- [ ] EnrollmentController
- [ ] Progress tracking

### Priority 3 (Polish)
- [ ] Pagination/filtering
- [ ] Search functionality
- [ ] Error handling/validation
- [ ] API documentation (Swagger)
- [ ] Unit tests
- [ ] Integration tests

---

## 💻 Code Structure Example

### Controller
```java
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    
    @GetMapping
    public ResponseEntity<Page<CourseDTO>> getAllCourses(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(courseService.getAllCourses(page, size));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CreateCourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(courseService.createCourse(request));
    }
}
```

### Service
```java
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    
    public Page<CourseDTO> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable)
            .map(this::convertToDTO);
    }
    
    public CourseDTO getCourseById(Long id) {
        return courseRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }
    
    private CourseDTO convertToDTO(Course course) {
        // Convert to DTO
    }
}
```

---

## ✅ Checklist for Implementation

- [ ] Create `AuthService` - handle registration/login logic
- [ ] Create `CourseService` - CRUD operations
- [ ] Create `LessonService` - CRUD operations
- [ ] Create `UserService` (extends from existing) - profile management
- [ ] Create `EnrollmentService` - enrollment logic
- [ ] Create DTOs for requests/responses
- [ ] Create exception classes
- [ ] Add `@PreAuthorize` annotations for role-based access
- [ ] Add `@Validated` for input validation
- [ ] Add error handlers with `@RestControllerAdvice`
- [ ] Add API documentation comments
- [ ] Write unit tests
- [ ] Test all endpoints with Postman/Thunder Client

---

This is your complete API specification. Use it to guide your backend implementation! 🚀
