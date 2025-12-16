# 🐳 Docker CLI Guide - Monorepo Image Building

## 📋 Overview

Your new **Dockerfile.monorepo** creates a single Docker image that:
- ✅ Builds React frontend (Node.js)
- ✅ Copies React output into Spring Boot
- ✅ Builds Spring Boot backend (Maven)
- ✅ Runs everything on Java 17 JRE (one container)

**No docker-compose needed!** One image = One container.

---

## 🎯 Quick Commands

### Build the image
```bash
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro

docker build -f Dockerfile.monorepo -t bangla-lms:latest .
```

### Run the container
```bash
docker run -p 8080:8080 bangla-lms:latest
```

### Access your app
```
Frontend: http://localhost:8080
Backend API: http://localhost:8080/api/courses
```

---

## 🔧 Complete Docker CLI Commands

### 1. Build Image (Development)
```bash
# Build with latest tag
docker build -f Dockerfile.monorepo -t bangla-lms:latest .

# Build with version tag
docker build -f Dockerfile.monorepo -t bangla-lms:1.0.0 .

# Build with multiple tags
docker build -f Dockerfile.monorepo -t bangla-lms:latest -t bangla-lms:1.0.0 .

# Build with no cache (force rebuild)
docker build -f Dockerfile.monorepo --no-cache -t bangla-lms:latest .

# Build with progress output
docker build -f Dockerfile.monorepo -t bangla-lms:latest --progress=plain .
```

### 2. Run Container (Foreground)
```bash
# Run with port mapping
docker run -p 8080:8080 bangla-lms:latest

# Run in detached mode (background)
docker run -d -p 8080:8080 --name bangla-app bangla-lms:latest

# Run with environment variables
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:h2:file:/data/db \
  -e JWT_SECRET=your-secret-key-here \
  bangla-lms:latest

# Run with custom Java options
docker run -p 8080:8080 \
  -e JAVA_OPTS="-Xmx1g -Xms512m" \
  bangla-lms:latest

# Run with volume for persistent data
docker run -p 8080:8080 \
  -v bangla-data:/data \
  bangla-lms:latest

# Run with all options
docker run -d \
  --name bangla-app \
  -p 8080:8080 \
  -v bangla-data:/data \
  -e SPRING_DATASOURCE_URL=jdbc:h2:file:/data/db \
  -e JWT_SECRET=your-secret-key \
  -e JAVA_OPTS="-Xmx1g -Xms256m" \
  --health-interval=30s \
  --health-timeout=10s \
  bangla-lms:latest
```

### 3. View Logs
```bash
# View logs from running container
docker logs <container-id>

# View logs with last 100 lines
docker logs --tail 100 <container-id>

# Follow logs (live)
docker logs -f <container-id>

# View logs with timestamps
docker logs -t <container-id>

# Example with container name
docker logs -f bangla-app
```

### 4. Manage Container
```bash
# List all containers
docker ps -a

# List only running containers
docker ps

# Stop container
docker stop bangla-app

# Start stopped container
docker start bangla-app

# Remove container
docker rm bangla-app

# Remove running container
docker rm -f bangla-app

# Inspect container details
docker inspect bangla-app

# View container resource usage
docker stats bangla-app
```

### 5. Manage Images
```bash
# List all images
docker images

# List images matching pattern
docker images bangla*

# Remove image
docker rmi bangla-lms:latest

# Remove unused images
docker image prune

# Tag image for Docker Hub
docker tag bangla-lms:latest your-username/bangla-lms:latest

# View image history/layers
docker history bangla-lms:latest

# Inspect image details
docker image inspect bangla-lms:latest
```

### 6. Push to Docker Hub (Production)
```bash
# Login to Docker Hub
docker login

# Tag image with Docker Hub username
docker tag bangla-lms:latest your-username/bangla-lms:latest

# Push image
docker push your-username/bangla-lms:latest

# Push with version
docker push your-username/bangla-lms:1.0.0
```

### 7. Execute Commands in Container
```bash
# Open interactive shell in running container
docker exec -it bangla-app sh

# Run a single command
docker exec bangla-app ls -la /app

# Run Java command
docker exec bangla-app java -version

# Check if API is responding
docker exec bangla-app wget -q -O- http://localhost:8080/api/courses
```

### 8. Copy Files To/From Container
```bash
# Copy file from container to host
docker cp bangla-app:/app/app.jar ./app.jar

# Copy file from host to container
docker cp ./config.properties bangla-app:/app/

# Copy directory from container
docker cp bangla-app:/data ./data-backup
```

### 9. Clean Up (Remove Everything)
```bash
# Stop all containers
docker stop $(docker ps -aq)

# Remove all stopped containers
docker container prune

# Remove all unused images
docker image prune -a

# Remove all unused volumes
docker volume prune

# Complete cleanup (danger!)
docker system prune -a
```

---

## 📊 Build Process Visualization

When you run `docker build`:

```
Step 1: Build React
├─ FROM node:20.12-alpine
├─ npm ci (install deps)
├─ COPY frontend/
├─ npm run build
└─ Output: frontend/dist/ ✓

Step 2: Build Spring Boot
├─ FROM eclipse-temurin:17-jdk
├─ Install Maven
├─ COPY backend/pom.xml
├─ COPY backend/src/
├─ COPY --from=frontend-builder (React dist/)
│  └─ Placed in: backend/src/main/resources/static/
├─ mvn clean package
└─ Output: backend/target/bangla-learning-lms-1.0.0.jar ✓

Step 3: Runtime
├─ FROM eclipse-temurin:17-jre
├─ COPY app.jar (from builder)
├─ EXPOSE 8080
└─ RUN java -jar app.jar ✓

Final Image: ~500MB ✓
```

---

## 🎯 Common Workflows

### Workflow 1: Development Build
```bash
# Navigate to project
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro

# Build image
docker build -f Dockerfile.monorepo -t bangla-lms:dev .

# Run container
docker run -d -p 8080:8080 --name dev-app bangla-lms:dev

# Check logs
docker logs -f dev-app

# Stop when done
docker stop dev-app
```

### Workflow 2: Production Release
```bash
# Build with version
docker build -f Dockerfile.monorepo -t bangla-lms:1.0.0 .

# Tag for Docker Hub
docker tag bangla-lms:1.0.0 your-username/bangla-lms:1.0.0
docker tag bangla-lms:1.0.0 your-username/bangla-lms:latest

# Push to registry
docker push your-username/bangla-lms:1.0.0
docker push your-username/bangla-lms:latest

# Run on production server
docker run -d -p 8080:8080 \
  --name bangla-prod \
  -v bangla-prod-data:/data \
  your-username/bangla-lms:1.0.0
```

### Workflow 3: Rebuild and Restart
```bash
# Stop old container
docker stop bangla-app

# Remove old container
docker rm bangla-app

# Remove old image
docker rmi bangla-lms:latest

# Build new image
docker build -f Dockerfile.monorepo -t bangla-lms:latest .

# Run new container
docker run -d -p 8080:8080 --name bangla-app bangla-lms:latest

# Verify
docker logs -f bangla-app
```

### Workflow 4: Debug Build Issues
```bash
# Build with verbose output
docker build -f Dockerfile.monorepo --progress=plain -t bangla-lms:latest .

# If build fails, run intermediate stage
docker build -f Dockerfile.monorepo --target frontend-builder -t frontend-test .

# Test individual stage
docker run -it frontend-test sh
```

---

## 📝 Environment Variables

### Spring Boot Configuration
```bash
# Database
-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/bangla
-e SPRING_DATASOURCE_USERNAME=root
-e SPRING_DATASOURCE_PASSWORD=secret

# JWT
-e JWT_SECRET=your-secret-key-minimum-32-characters
-e JWT_EXPIRATION=86400000

# Server
-e SERVER_PORT=8080
-e SERVER_SERVLET_CONTEXT_PATH=/api

# Logging
-e LOGGING_LEVEL_ROOT=INFO
-e LOGGING_LEVEL_COM_BANGLA=DEBUG
```

### Java Options
```bash
# Memory settings
-e JAVA_OPTS="-Xmx1g -Xms512m"

# Garbage collection
-e JAVA_OPTS="-Xmx1g -XX:+UseG1GC"

# Debugging
-e JAVA_OPTS="-Xmx1g -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

# All together
docker run -p 8080:8080 \
  -e JAVA_OPTS="-Xmx1g -Xms512m" \
  -e SPRING_DATASOURCE_URL=jdbc:h2:file:/data/db \
  bangla-lms:latest
```

---

## 🔍 Troubleshooting

### Build Fails: "Frontend build failed"
```bash
# Check if frontend files exist
docker build -f Dockerfile.monorepo --target frontend-builder -t test .
docker run -it test ls -la frontend/

# Common issues:
# - frontend/package.json missing
# - npm run build not in package.json
# - Node.js version mismatch
```

### Build Fails: "Backend build failed"
```bash
# Check if backend files exist
docker build -f Dockerfile.monorepo --target backend-builder -t test .
docker run -it test ls -la backend/

# Common issues:
# - backend/pom.xml missing
# - Maven dependencies not available
# - Java version mismatch
```

### Container Won't Start
```bash
# Check logs
docker logs bangla-app

# Common issues:
# - Port 8080 already in use
# - Memory limit too low
# - Database connection failed

# Solution: Use different port
docker run -p 9090:8080 bangla-lms:latest
```

### API Returns 404
```bash
# Check if React static files are served
docker exec bangla-app ls -la /app/src/main/resources/static

# Should show: index.html, assets/, etc.
```

### Port Already in Use
```bash
# Find what's using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or use different port
docker run -p 9090:8080 bangla-lms:latest
```

---

## 📊 Docker Statistics

### View Resource Usage
```bash
# Live stats for all containers
docker stats

# Stats for specific container
docker stats bangla-app

# Save stats to file
docker stats --no-stream > docker-stats.txt
```

### Expected Resource Usage
```
Image Size:        ~300MB
Container Memory:  ~200-400MB (depends on workload)
Startup Time:      ~10-15 seconds
CPU Usage:         <5% idle, 20-30% under load
```

---

## 🚀 Performance Tips

### Build Optimization
```bash
# Use cache when possible
docker build -f Dockerfile.monorepo -t bangla-lms:latest .

# Clear cache if needed (full rebuild)
docker build -f Dockerfile.monorepo --no-cache -t bangla-lms:latest .

# Check build time
time docker build -f Dockerfile.monorepo -t bangla-lms:latest .
```

### Runtime Optimization
```bash
# Set proper memory limits
docker run -m 1g bangla-lms:latest

# Use Docker volumes for better I/O
docker volume create bangla-data
docker run -v bangla-data:/data bangla-lms:latest

# Limit CPU
docker run --cpus=2 bangla-lms:latest
```

---

## 📚 Additional Resources

**Docker Docs:**
- Build: https://docs.docker.com/engine/reference/commandline/build/
- Run: https://docs.docker.com/engine/reference/commandline/run/
- Logs: https://docs.docker.com/engine/reference/commandline/logs/

**Best Practices:**
- Multi-stage builds: https://docs.docker.com/build/building/multi-stage/
- Layer caching: https://docs.docker.com/build/cache/
- Health checks: https://docs.docker.com/engine/reference/builder/#healthcheck

---

## ✅ Verification Checklist

After building and running:

- [ ] Image built successfully: `docker images | grep bangla-lms`
- [ ] Container running: `docker ps | grep bangla-lms`
- [ ] Port 8080 accessible: `curl http://localhost:8080`
- [ ] Frontend loads: `curl http://localhost:8080 | grep -q "<!DOCTYPE" && echo "OK"`
- [ ] API responds: `curl http://localhost:8080/api/courses`
- [ ] Health check passes: `docker inspect bangla-app | grep -A5 "State"`

---

## 🎉 You're Ready!

Your monorepo is ready for Docker deployment!

**Next steps:**
1. Build: `docker build -f Dockerfile.monorepo -t bangla-lms:latest .`
2. Run: `docker run -p 8080:8080 bangla-lms:latest`
3. Access: http://localhost:8080
4. Deploy: Push to Docker Hub and run anywhere!

Happy Dockering! 🐳
