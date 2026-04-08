# ============================================
# STAGE 1: BUILD THE REACT APPLICATION
# ============================================
# Use Node.js Alpine (lightweight Linux) for building
FROM node:20-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json first
# This allows Docker to cache the npm install layer
# if dependencies haven't changed
COPY package*.json ./

# Install all dependencies (including devDependencies)
RUN npm ci

# Copy the rest of the application code
COPY . .

# Build the React app for production
# This creates optimized static files in the /app/dist folder
RUN npm run build

# ============================================
# STAGE 2: SERVE WITH NGINX
# ============================================
# Use Nginx Alpine for serving static files
FROM nginx:alpine

# Copy the built React app from the builder stage
# to Nginx's default public directory
COPY --from=builder /app/dist /usr/share/nginx/html

# Copy custom Nginx configuration
# This handles React Router and proxies API calls to the backend
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 (HTTP)
EXPOSE 80

# Start Nginx in the foreground
# The 'daemon off' directive keeps Nginx running as the main process
CMD ["nginx", "-g", "daemon off;"]
