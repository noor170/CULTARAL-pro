# 🐳 Docker Deployment Guide

This guide explains how to build and run the Bangla Learning LMS application using Docker.

## 📋 Prerequisites

- Docker installed ([Download Docker](https://www.docker.com/get-started))
- Docker Compose installed (included with Docker Desktop)

## 🚀 Quick Start

### Option 1: Using Docker Compose (Recommended)

Build and start both frontend and backend services:

```bash
docker-compose up --build
```

This will:
- Build the backend Spring Boot application
- Build the frontend React application
- Start both services with proper networking
- Persist the H2 database in the `./data` directory

**Access the application:**
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

### Option 2: Build Services Individually

#### Build Backend:
```bash
cd backend
docker build -t bangla-lms-backend .
docker run -p 8080:8080 -v $(pwd)/../data:/data bangla-lms-backend
```

#### Build Frontend:
```bash
docker build -t bangla-lms-frontend .
docker run -p 3000:80 bangla-lms-frontend
```

## 🛠️ Common Commands

### Start services in background (detached mode):
```bash
docker-compose up -d
```

### Stop services:
```bash
docker-compose down
```

### View logs:
```bash
docker-compose logs -f
```

### View logs for specific service:
```bash
docker-compose logs -f backend
docker-compose logs -f frontend
```

### Rebuild after code changes:
```bash
docker-compose up --build
```

### Remove all containers and volumes:
```bash
docker-compose down -v
```

## 📁 File Structure

```
.
├── docker-compose.yml          # Orchestrates both services
├── Dockerfile                  # Frontend build (React + Nginx)
├── nginx.conf                  # Nginx configuration
├── .dockerignore              # Files to exclude from frontend image
├── backend/
│   ├── Dockerfile             # Backend build (Spring Boot)
│   └── .dockerignore          # Files to exclude from backend image
└── data/                      # H2 database persistence (created automatically)
```

## 🔧 Configuration

### Backend Environment Variables

Edit `docker-compose.yml` to customize backend settings:

```yaml
environment:
  - SPRING_DATASOURCE_URL=jdbc:h2:file:/data/lmsdb
  - SERVER_PORT=8080
```

### Frontend API Proxy

The Nginx configuration automatically proxies `/api` requests to the backend, avoiding CORS issues.

If you need to change the backend URL, edit `nginx.conf`:

```nginx
location /api {
    proxy_pass http://backend:8080;
}
```

## 📊 Database Persistence

The H2 database is stored in `./data/lmsdb.mv.db` on your host machine. This means:
- ✅ Data persists between container restarts
- ✅ You can backup the `data` folder
- ✅ Database survives `docker-compose down`

To reset the database, simply delete the `data` folder:

```bash
rm -rf data/
```

## 🐛 Troubleshooting

### Port Already in Use

If ports 3000 or 8080 are already in use, edit `docker-compose.yml`:

```yaml
ports:
  - "4000:80"  # Change 3000 to 4000 for frontend
  - "9090:8080"  # Change 8080 to 9090 for backend
```

### Backend Not Responding

Check if the backend container is running:

```bash
docker-compose ps
docker-compose logs backend
```

### Frontend Can't Connect to Backend

1. Ensure both services are running
2. Check the backend health: http://localhost:8080/api/courses
3. Review Nginx proxy configuration in `nginx.conf`

## 📦 Deployment Tips

### For Production:

1. **Use environment variables for secrets:**
   ```yaml
   environment:
     - JWT_SECRET=${JWT_SECRET}
   ```

2. **Enable HTTPS** with a reverse proxy (Nginx, Traefik, or Caddy)

3. **Use a production database** (PostgreSQL, MySQL) instead of H2:
   ```yaml
   environment:
     - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/lmsdb
     - SPRING_DATASOURCE_USERNAME=postgres
     - SPRING_DATASOURCE_PASSWORD=secure_password
   ```

4. **Add resource limits:**
   ```yaml
   deploy:
     resources:
       limits:
         cpus: '1'
         memory: 512M
   ```

## 🎯 Benefits of This Docker Setup

✅ **Multi-stage builds** - Smaller final images (backend: ~180MB, frontend: ~25MB)
✅ **Layer caching** - Faster rebuilds when code changes
✅ **Data persistence** - Database survives restarts
✅ **No CORS issues** - Nginx proxies API requests
✅ **React Router support** - Refresh works on all routes
✅ **Production-ready** - Nginx serves optimized static files
✅ **Easy handoff** - Single command to start everything

## 📝 Notes

- The backend takes ~30-40 seconds to start (Spring Boot initialization)
- The frontend waits for the backend to be healthy before starting
- All logs are visible with `docker-compose logs -f`
- The H2 console is enabled for development/debugging
