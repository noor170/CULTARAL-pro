# 🔧 Docker Build Error: Image Metadata Loading Failed

## ❌ Your Error
```
ERROR [frontend internal] load metadata for docker.io/library/nginx:alpine
ERROR [backend internal] load metadata for docker.io/library/openjdk:17-slim
```

---

## 🎯 Root Causes & Solutions

### Cause 1: Network/DNS Issues (Most Common)
Your Docker can't reach Docker Hub to download base images.

**Fix:**
```bash
# 1. Verify Docker is running
docker ps
# If this fails, Docker daemon not running → open /Applications/Docker.app

# 2. Check internet connectivity
ping -c 2 8.8.8.8
# Should show packets sent/received

# 3. Test Docker Hub access
docker pull hello-world
# If this fails, network is the issue
```

### Cause 2: Docker Daemon Issues
Sometimes Docker needs a restart.

**Fix:**
```bash
# Close Docker Desktop completely
# Quit Docker: CMD+Q

# Wait 10 seconds

# Reopen Docker
open /Applications/Docker.app

# Wait 30 seconds for daemon to start

# Try again
docker-compose up --build
```

### Cause 3: Docker Buildkit Issues
Buildkit caching can cause problems.

**Fix:**
```bash
# Clear Docker builder cache
docker builder prune -a

# Disable Buildkit temporarily
DOCKER_BUILDKIT=0 docker-compose up --build
```

### Cause 4: Firewall/Proxy
Corporate firewalls can block Docker Hub.

**Fix:**
```bash
# Try pulling base images manually
docker pull openjdk:17-slim
docker pull node:18-alpine
docker pull nginx:alpine

# If these work, issue was timeout
# If these fail, you have network issues
```

---

## ✅ Step-by-Step Fix

### Step 1: Verify Docker is Running
```bash
docker --version
docker ps
```
**Expected output:** Docker version and no errors

### Step 2: Verify Internet Connection
```bash
# Test Google DNS
ping -c 2 8.8.8.8

# Test Docker Hub
docker pull hello-world
```
**Expected:** Successful download

### Step 3: Clear Docker Cache
```bash
docker builder prune -a
docker image prune -a
docker system prune -a
```

### Step 4: Try Building Again
```bash
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro

# Disable Buildkit first time
DOCKER_BUILDKIT=0 docker-compose up --build

# OR with verbose output to see progress
docker-compose up --build --verbose
```

### Step 5: If Still Failing
```bash
# Pull base images manually first
docker pull openjdk:17-slim
docker pull node:18-alpine  
docker pull nginx:alpine

# Then try compose again
docker-compose up --build
```

---

## 🔍 Detailed Troubleshooting

### Check Docker Daemon Status
```bash
# See if Docker process is running
ps aux | grep Docker

# Check Docker logs
log show --predicate 'process == "Docker"' --last 1h
```

### View Full Docker-Compose Output
```bash
# Run with verbose logging
docker-compose up --build --verbose 2>&1 | tail -100

# Or save to file for analysis
docker-compose up --build > docker_build.log 2>&1
```

### Test Individual Dockerfiles
```bash
# Test backend build
cd backend
docker build -t bangla-backend:test .

# Test frontend build
cd ..
docker build -t bangla-frontend:test -f Dockerfile .
```

### Check Docker Desktop Settings
```bash
# Verify Docker has internet access
# Go to: Docker Desktop → Preferences → Resources
# Check:
# - Memory: At least 4GB
# - CPU: At least 2 cores
# - Disk: At least 20GB free
```

---

## 🚀 Alternative: Build Without Docker Locally

If Docker continues to fail, run locally without containers:

```bash
# Terminal 1: Backend
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro/backend
mvn clean spring-boot:run

# Terminal 2: Frontend
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro
npm install
npm run dev
```

**Access:**
- Frontend: http://localhost:5173
- Backend: http://localhost:8080

---

## 💾 Docker Disk Space Issue?

If you're running low on disk:

```bash
# See Docker's disk usage
docker system df

# Clean up everything unused
docker system prune -a --volumes

# This will free up 5-10GB typically
```

---

## 🌐 Network Issue? Try These

### If Behind Corporate Proxy
```bash
# Configure Docker proxy
# Edit: ~/.docker/config.json
{
  "proxies": {
    "default": {
      "httpProxy": "http://proxy.example.com:8080",
      "httpsProxy": "http://proxy.example.com:8080",
      "noProxy": "localhost,127.0.0.1"
    }
  }
}
```

### If on VPN
```bash
# Sometimes VPN interferes with Docker
# Try disconnecting VPN and running:
docker-compose up --build

# If it works, VPN is the issue
# Ask your IT to whitelist Docker Hub
```

---

## 📊 Diagnostic Checklist

Run this to diagnose issues:

```bash
#!/bin/bash
echo "=== Docker Diagnostics ==="

echo "1. Docker Version"
docker --version

echo -e "\n2. Docker Daemon Running"
docker ps > /dev/null && echo "✅ Daemon running" || echo "❌ Daemon not running"

echo -e "\n3. Internet Connectivity"
ping -c 1 8.8.8.8 > /dev/null && echo "✅ Internet OK" || echo "❌ No internet"

echo -e "\n4. Docker Hub Access"
docker pull hello-world > /dev/null 2>&1 && echo "✅ Docker Hub OK" || echo "❌ Docker Hub unreachable"

echo -e "\n5. Disk Space"
docker system df

echo -e "\n6. Docker Compose Version"
docker-compose --version

echo -e "\n7. Base Images"
docker image ls | grep -E "openjdk|node|nginx"

echo -e "\n=== End Diagnostics ==="
```

Save as `docker-diagnose.sh` and run:
```bash
chmod +x docker-diagnose.sh
./docker-diagnose.sh
```

---

## 🎯 Quick Fixes (In Order)

1. **Restart Docker Desktop**
   ```bash
   # Quit: CMD+Q
   # Reopen: open /Applications/Docker.app
   # Wait 30 seconds
   ```

2. **Clear Cache**
   ```bash
   docker system prune -a
   docker builder prune -a
   ```

3. **Test Connectivity**
   ```bash
   docker pull hello-world
   ```

4. **Try Build Again**
   ```bash
   docker-compose up --build
   ```

5. **If Still Failing: Use Local Development**
   ```bash
   # Backend
   mvn spring-boot:run
   
   # Frontend
   npm run dev
   ```

---

## 📞 When All Else Fails

### Option A: Check Docker Desktop Status
- Open Docker Desktop → Preferences
- Check "Diagnose & Feedback"
- Click "Upload to Docker"

### Option B: Reinstall Docker
```bash
# Backup Docker images first
docker save -o docker-backup.tar $(docker images -q)

# Uninstall Docker (remove from Applications)
# Reinstall from: https://www.docker.com/products/docker-desktop/

# Restore images
docker load -i docker-backup.tar
```

### Option C: Use Local Development Only
```bash
# Skip Docker completely during development
cd backend && mvn spring-boot:run  # Terminal 1
cd .. && npm run dev               # Terminal 2
```

---

## ✨ What's Happening

When you run `docker-compose up --build`:

1. Docker reads `docker-compose.yml`
2. For each service (backend, frontend), it finds the Dockerfile
3. Docker starts building each image layer by layer
4. **First step:** Pull the base images from Docker Hub
   - Backend needs: `openjdk:17-slim`
   - Frontend needs: `node:18-alpine`, `nginx:alpine`
5. **Error occurring at:** Fetching metadata from Docker Hub

**Metadata fetching fails when:**
- No internet connection
- Docker daemon not responding
- Docker Hub is down
- Network/firewall blocking
- DNS resolution issues
- Buildkit caching problems

---

## 🎓 Understanding the Error

```
ERROR [frontend internal] load metadata for docker.io/library/nginx:alpine: 10.5s
```

This means:
- Docker tried for 10.5 seconds to fetch `nginx:alpine` from Docker Hub
- It couldn't get metadata (image info)
- Build was cancelled

---

## 🚀 Next Steps

1. **Run diagnostics:** Use the checklist above
2. **Fix the issue:** Follow the relevant cause from above
3. **Try again:** `docker-compose up --build`
4. **Still failing?** Use local development instead

---

**Most common fix: Restart Docker Desktop and clear cache** ✨

```bash
# Kill Docker
pkill -9 Docker

# Wait 5 seconds
sleep 5

# Reopen
open /Applications/Docker.app

# Wait 30 seconds

# Clear cache
docker system prune -a

# Try again
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro
docker-compose up --build
```

**If that doesn't work, use local development:**
```bash
# Terminal 1
cd backend && mvn spring-boot:run

# Terminal 2
npm run dev
```

---

**Need more help?** Check `QUICK_START.md` for alternative local setup! 🎉
