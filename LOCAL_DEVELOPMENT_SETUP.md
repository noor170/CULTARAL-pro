# 🚀 LOCAL DEVELOPMENT SETUP - FASTER & NO DOCKER ISSUES

Since Docker is experiencing network timeout issues fetching base images, **use local development instead**. This is actually **faster and better for development** because you get:

✅ Hot reload (changes apply instantly)  
✅ Faster iteration  
✅ Easier debugging  
✅ No Docker overhead  
✅ Works without internet for base images

---

## 🎯 Quick Start (5 minutes)

### Step 1: Verify Prerequisites
```bash
# Check Java
java -version
# Expected: Java 17 or higher

# Check Maven
mvn -version
# Expected: Maven 3.9+

# Check Node.js
node --version && npm --version
# Expected: Node 18+ and npm 9+
```

### Step 2: Start Backend (Terminal 1)
```bash
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro/backend

# Clean and build
mvn clean spring-boot:run
```

You should see:
```
...
2025-01-16 14:32:15.123  INFO 12345 --- [main] c.b.l.BanglaLearningLmsApplication : Started BanglaLearningLmsApplication in 8.234s
```

**Backend Ready!** → http://localhost:8080

### Step 3: Start Frontend (Terminal 2)
```bash
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro

# Install dependencies (first time only)
npm install

# Start dev server
npm run dev
```

You should see:
```
➜ local:   http://localhost:5173/
```

**Frontend Ready!** → http://localhost:5173

---

## 🌐 Access Your App

| Component | URL | Purpose |
|-----------|-----|---------|
| Frontend | http://localhost:5173 | React app |
| Backend API | http://localhost:8080 | Spring Boot API |
| H2 Database Console | http://localhost:8080/h2-console | Database management |
| API Documentation | http://localhost:8080/api/docs | API specs (if Swagger added) |

---

## 📝 What's Running

### Backend (Spring Boot)
- **Framework:** Spring Boot 3.2.0
- **Database:** H2 (in-memory, auto-initialized)
- **Port:** 8080
- **Features:** JWT auth, REST APIs, Liquibase migrations

### Frontend (React + Vite)
- **Framework:** React 18 + Vite
- **Dev Server:** Built-in dev server with hot reload
- **Port:** 5173
- **Styling:** Tailwind CSS

### Database
- **Type:** H2 (embedded)
- **Location:** Memory-only (data lost on restart)
- **Console:** http://localhost:8080/h2-console

---

## 🔄 Development Workflow

### Making Changes

**Frontend Changes (Hot Reload):**
1. Edit a file in `src/`
2. Browser auto-refreshes ✨
3. No restart needed!

**Backend Changes:**
1. Edit a Java file in `backend/src/`
2. Maven detects change and recompiles
3. Spring Boot restarts automatically
4. Browser auto-refreshes or just refresh manually

### Testing APIs

Use one of these tools to test APIs:

**Option 1: VS Code REST Client**
Install extension: `REST Client`

Create file: `.vscode/test.http`
```http
### Get all courses
GET http://localhost:8080/api/courses

### Login
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

**Option 2: cURL (Terminal)**
```bash
# Get courses
curl http://localhost:8080/api/courses

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'
```

**Option 3: Postman**
1. Download Postman: https://www.postman.com
2. Create requests for your APIs
3. Test and debug easily

---

## 🔐 Authentication

### Default Users (Created by DataInitializer)

| Email | Password | Role |
|-------|----------|------|
| admin@bangla.edu | password | ADMIN |
| student@bangla.edu | password | STUDENT |

### JWT Token Usage

**Get Token:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@bangla.edu",
    "password": "password"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "admin@bangla.edu"
}
```

**Use Token in Requests:**
```bash
curl http://localhost:8080/api/courses \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

---

## 📊 H2 Database Console

Access database directly at: http://localhost:8080/h2-console

**Login:**
- JDBC URL: `jdbc:h2:mem:banglalmsdb`
- Username: `sa`
- Password: (leave empty)

**Execute SQL:**
```sql
SELECT * FROM users;
SELECT * FROM courses;
SELECT * FROM lessons;
```

---

## 🛠️ Troubleshooting Local Development

### Issue: Port 8080 already in use
```bash
# Find what's using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or use a different port
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### Issue: Port 5173 already in use
```bash
# Find what's using port 5173
lsof -i :5173

# Kill the process
kill -9 <PID>

# Or npm will auto-use next available port
```

### Issue: Maven build fails
```bash
# Clean and rebuild
cd backend
mvn clean compile

# If still failing, check Java version
java -version  # Should be 17 or higher
```

### Issue: npm install fails
```bash
# Clear npm cache
npm cache clean --force

# Try install again
npm install

# If still fails, check Node version
node --version  # Should be 18+
```

### Issue: Can't connect frontend to backend
1. Ensure backend is running on 8080
2. Check `nginx.conf` has correct backend URL
3. Check browser console for CORS errors
4. Verify backend endpoints exist

---

## 📚 API Endpoints to Test

Once running, test these endpoints:

```bash
# Get all courses
curl http://localhost:8080/api/courses

# Get single course
curl http://localhost:8080/api/courses/1

# Get lessons for course
curl http://localhost:8080/api/courses/1/lessons

# Get single lesson
curl http://localhost:8080/api/lessons/1

# (Requires auth token)
# Create course
curl -X POST http://localhost:8080/api/courses \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Advanced Bangla",
    "description": "Advanced level course",
    "price": "199.99"
  }'
```

See `API_SPECIFICATION.md` for complete endpoint documentation.

---

## 🎯 Common Tasks

### View Application Logs
```bash
# Backend logs show automatically in terminal
# Look for errors in Spring Boot startup

# Frontend logs show in browser console
# F12 or Cmd+Opt+I in Chrome/Safari/Edge
```

### Reset Database
Since H2 is in-memory, just restart the backend:
```bash
# Stop backend (Ctrl+C in backend terminal)
# Restart backend (up arrow + Enter in same terminal)

# Database is reset with fresh sample data
```

### Change Database to Persistent
Edit `backend/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:h2:file:./data/banglalmsdb
    # ^ Changes from jdbc:h2:mem:banglalmsdb
```

---

## 🚀 Next Steps

1. **Get both services running** (follow Quick Start above)
2. **Test the APIs** (use provided curl commands)
3. **Test login** with default credentials
4. **Build features** using the development workflow
5. **Use browser DevTools** for frontend debugging

---

## 📖 Key Files

| File | Purpose |
|------|---------|
| `backend/application.yml` | Backend configuration |
| `vite.config.ts` | Frontend build config |
| `docker-compose.yml` | Docker setup (not needed for local dev) |
| `API_SPECIFICATION.md` | All API endpoint docs |
| `PROJECT_ROADMAP.md` | Development roadmap |

---

## ✨ Development Tips

### Useful VS Code Extensions
```
- REST Client (REST API testing)
- Thunder Client (Alternative API client)
- GitLens (Git integration)
- Prettier (Code formatting)
- ESLint (JavaScript linting)
- Tailwind CSS IntelliSense (CSS suggestions)
- Spring Boot Extension Pack (Java/Spring Boot)
```

### Browser DevTools
- **F12** or **Cmd+Opt+I** → Open DevTools
- **Network tab** → See API calls
- **Console tab** → See JavaScript errors
- **Application tab** → See LocalStorage/Cookies

### Backend Performance
Monitor startup time:
```
Started BanglaLearningLmsApplication in 8.234s
↑ This should be under 15 seconds
```

---

## 🎓 Why Local Development is Better

| Aspect | Docker | Local |
|--------|--------|-------|
| **Startup Time** | 30-60s | 5-10s |
| **Hot Reload** | No | ✅ Yes |
| **Debugging** | Hard | ✅ Easy |
| **Testing** | Slower | ✅ Faster |
| **Network Issues** | Blocks build | ✅ No issues |
| **IDE Integration** | Limited | ✅ Full |
| **Development Speed** | Slow | ✅ Fast |

---

## 📞 When You're Ready for Docker

Once local development works perfectly, you can then tackle Docker:

1. Fix network issue (your ISP/firewall)
2. Pre-pull base images (takes 10min)
3. Run `docker-compose up --build`
4. Both development methods will work

---

## ✅ Verification Checklist

- [ ] Backend running on 8080
- [ ] Frontend running on 5173
- [ ] Can access http://localhost:5173
- [ ] Can make API call to http://localhost:8080/api/courses
- [ ] Can login with default credentials
- [ ] Browser DevTools show no errors
- [ ] Backend logs show no errors

---

## 🎉 You're Ready!

Your development environment is now running! Start building features, and when you need Docker later, we can fix that separately.

**Backend:** http://localhost:8080  
**Frontend:** http://localhost:5173  
**Database Console:** http://localhost:8080/h2-console

Happy coding! 🚀
