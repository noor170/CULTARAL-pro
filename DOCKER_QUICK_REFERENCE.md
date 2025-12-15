# 🐳 Docker Quick Reference Card

## Build & Run Your Monorepo Image

### 1. Build Image (5-10 minutes)
```bash
docker build -f Dockerfile.monorepo -t bangla-lms:latest .
```

### 2. Run Container
```bash
docker run -p 8080:8080 bangla-lms:latest
```

### 3. Access App
```
🌐 Frontend + Backend: http://localhost:8080
📚 API: http://localhost:8080/api/courses
```

---

## Essential Commands

| Command | What It Does | Example |
|---------|-------------|---------|
| **build** | Create image from Dockerfile | `docker build -t bangla-lms:latest .` |
| **run** | Start container from image | `docker run -p 8080:8080 bangla-lms:latest` |
| **ps** | List running containers | `docker ps` |
| **logs** | View container output | `docker logs -f bangla-app` |
| **stop** | Stop running container | `docker stop bangla-app` |
| **start** | Restart stopped container | `docker start bangla-app` |
| **rm** | Delete container | `docker rm bangla-app` |
| **rmi** | Delete image | `docker rmi bangla-lms:latest` |
| **exec** | Run command in container | `docker exec bangla-app sh` |
| **images** | List images | `docker images` |
| **stats** | View resource usage | `docker stats bangla-app` |
| **inspect** | View container details | `docker inspect bangla-app` |

---

## Quick Build & Run

### Full Cycle (Build + Run)
```bash
# 1. Build
docker build -f Dockerfile.monorepo -t bangla-lms:latest .

# 2. Run
docker run -d -p 8080:8080 --name bangla-app bangla-lms:latest

# 3. Check logs
docker logs -f bangla-app

# 4. Stop when done
docker stop bangla-app
```

### With Data Volume (Persistent)
```bash
# Create volume
docker volume create bangla-data

# Run with volume
docker run -d -p 8080:8080 \
  -v bangla-data:/data \
  --name bangla-app \
  bangla-lms:latest
```

### With Environment Variables
```bash
docker run -d -p 8080:8080 \
  -e JAVA_OPTS="-Xmx1g" \
  -e JWT_SECRET=your-secret-key \
  --name bangla-app \
  bangla-lms:latest
```

---

## Status Checks

### Is Docker Running?
```bash
docker ps
# Shows: CONTAINER ID IMAGE COMMAND CREATED STATUS PORTS NAMES
```

### Is Container Healthy?
```bash
docker inspect bangla-app --format='{{.State.Health.Status}}'
# Shows: healthy / unhealthy
```

### View Real-time Stats
```bash
docker stats bangla-app
# Shows: CPU%, Memory%, Network I/O, Block I/O
```

### Check Logs for Errors
```bash
docker logs bangla-app | tail -50
# Shows last 50 lines of output
```

---

## Cleanup

### Stop & Remove Container
```bash
docker stop bangla-app
docker rm bangla-app
```

### Remove Image
```bash
docker rmi bangla-lms:latest
```

### Clean Everything (DANGER!)
```bash
# Remove unused containers, images, volumes
docker system prune -a
```

---

## Debugging

### Access Container Shell
```bash
docker exec -it bangla-app sh
# Now you're inside the container!
# Can run: ls, java -version, etc.
```

### View Application Logs
```bash
# Last 100 lines
docker logs --tail 100 bangla-app

# Follow in real-time (like tail -f)
docker logs -f bangla-app
```

### Test API from Inside Container
```bash
docker exec bangla-app wget -q -O- http://localhost:8080/api/courses
```

### Check File Exists in Container
```bash
docker exec bangla-app ls -la /app/app.jar
```

---

## Common Issues & Fixes

| Problem | Fix |
|---------|-----|
| **Port 8080 in use** | `docker run -p 9090:8080 ...` (use 9090) |
| **Container won't start** | `docker logs bangla-app` (check errors) |
| **Can't connect to API** | `docker exec bangla-app wget localhost:8080` |
| **Out of memory** | `docker run -m 2g ...` (increase to 2GB) |
| **Build too slow** | Use `docker build --progress=plain` (show details) |
| **Disk full** | `docker system prune -a` (clean up) |
| **Changes not reflected** | Rebuild: `docker build ... --no-cache` |

---

## Production Deployment

### Tag for Docker Hub
```bash
docker tag bangla-lms:latest your-username/bangla-lms:latest
```

### Push to Registry
```bash
docker login
docker push your-username/bangla-lms:latest
```

### Run on Production Server
```bash
docker run -d \
  -p 8080:8080 \
  -v /prod/data:/data \
  -e JAVA_OPTS="-Xmx2g" \
  your-username/bangla-lms:latest
```

---

## File Structure

Your image contains:
```
/app/
├── app.jar                           # Spring Boot JAR
├── src/
│   └── main/
│       └── resources/
│           └── static/               # React files!
│               ├── index.html
│               ├── assets/
│               └── ...

Spring Boot serves:
├── /                                 # React frontend
├── /api/*                            # API endpoints
└── /h2-console                       # Database console
```

---

## Key Files

| File | Purpose |
|------|---------|
| `Dockerfile.monorepo` | Build instructions for image |
| `backend/pom.xml` | Maven Spring Boot config |
| `frontend/package.json` | React/Node.js config |
| `frontend/dist/` | Built React app (created during build) |

---

## Performance Targets

```
Build Time:        5-10 minutes (first build)
                   1-2 minutes (with cache)
Image Size:        ~300MB
Startup Time:      ~10-15 seconds
Memory Usage:      200-400MB
CPU Usage:         <5% idle
```

---

## Tips & Tricks

### 1. Faster Rebuilds
```bash
# Docker caches layers, only rebuilds what changed
# Keep Dockerfile changes at bottom for faster iteration
docker build -f Dockerfile.monorepo -t bangla-lms:latest .
```

### 2. Watch Logs in Real-time
```bash
docker logs -f bangla-app
# Ctrl+C to exit
```

### 3. Test Before Deploying
```bash
# Run locally first
docker run -p 8080:8080 bangla-lms:latest

# Then push to registry
docker push your-user/bangla-lms:latest
```

### 4. Keep Images Small
```bash
# Multi-stage build removes build tools from final image
# Your image: 300MB (not 2GB!)
docker images | grep bangla-lms
```

### 5. Use Volumes for Data
```bash
# Data survives container restart
docker volume create bangla-data
docker run -v bangla-data:/data bangla-lms:latest
```

---

## Next Steps

1. **Build:** 
   ```bash
   docker build -f Dockerfile.monorepo -t bangla-lms:latest .
   ```

2. **Run:**
   ```bash
   docker run -p 8080:8080 bangla-lms:latest
   ```

3. **Test:**
   ```bash
   curl http://localhost:8080
   ```

4. **Deploy:**
   ```bash
   docker push your-username/bangla-lms:latest
   ```

---

## 📚 Reference

- **Build Docs:** https://docs.docker.com/engine/reference/commandline/build/
- **Run Docs:** https://docs.docker.com/engine/reference/commandline/run/
- **Best Practices:** https://docs.docker.com/develop/dev-best-practices/

---

**Happy Dockering!** 🐳✨
