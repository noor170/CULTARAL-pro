# 🔧 Docker Setup Fix for macOS

## ❌ Your Error
```
Cannot connect to the Docker daemon at unix:///Users/macbookairm1/.docker/run/docker.sock
WARN[0000] /Users/macbookairm1/Documents/GitHub/CULTARAL-pro/docker-compose.yml: 
the attribute `version` is obsolete, it will be ignored
```

---

## ✅ Solution (3 Steps)

### Step 1: Start Docker Desktop
```bash
# Open Docker Desktop from Applications
open /Applications/Docker.app

# Wait 30 seconds for the Docker daemon to start
# You'll see the Docker icon in the menu bar at the top
```

### Step 2: Remove Version Line ✅ (Already Done!)
The `version: '3.8'` line has been removed from `docker-compose.yml`

### Step 3: Test Docker is Running
```bash
docker --version
# Should show: Docker version X.XX.X

docker-compose --version
# Should show: Docker Compose version X.XX.X
```

---

## 🚀 Run Your Application

### Option 1: Docker (Recommended for Testing)
```bash
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro
docker-compose up --build

# Then open browser:
# Frontend: http://localhost:3000
# Backend: http://localhost:8080
```

### Option 2: Local Development (Faster Feedback)
```bash
# Terminal 1: Backend
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro/backend
mvn spring-boot:run
# http://localhost:8080

# Terminal 2: Frontend
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro
npm run dev
# http://localhost:5173
```

---

## 🐛 Troubleshooting

### Still getting "Docker daemon not running"?

```bash
# Check if Docker is really running
docker ps

# If not, manually start Docker daemon on macOS:
open /Applications/Docker.app

# Or via command line (if Docker is installed):
colima start  # If using Colima
```

### Port 3000 or 8080 already in use?

Edit `docker-compose.yml`:
```yaml
ports:
  - "4000:80"    # Change 3000 to 4000
  - "9090:8080"  # Change 8080 to 9090
```

### Backend container keeps restarting?

Check logs:
```bash
docker-compose logs -f backend
docker-compose logs -f frontend
```

---

## ✨ What's Next?

1. ✅ Docker daemon running
2. ✅ docker-compose.yml fixed
3. **→ Read `PROJECT_ROADMAP.md` for development tasks**
4. **→ Start working on Backend/Frontend features**

---

**Need help?** Check the `PROJECT_ROADMAP.md` file for complete development guide!
