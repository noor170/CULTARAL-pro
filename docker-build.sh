#!/bin/bash

################################################################################
# DOCKER BUILD & DEPLOY SCRIPT FOR BANGLA LMS
################################################################################
# 
# This script automates building, testing, and deploying your Docker image
#
# Usage:
#   chmod +x docker-build.sh
#   ./docker-build.sh [command] [options]
#
# Commands:
#   build              - Build Docker image
#   run                - Build and run container
#   stop               - Stop running container
#   logs               - View container logs
#   shell              - Open shell in container
#   push               - Push to Docker Hub
#   clean              - Remove image and container
#   test               - Test API endpoints
#
# Examples:
#   ./docker-build.sh build
#   ./docker-build.sh run -p 9090:8080
#   ./docker-build.sh logs
#   ./docker-build.sh push myusername/bangla-lms
#
################################################################################

set -e

# Configuration
DOCKERFILE="Dockerfile.monorepo"
IMAGE_NAME="bangla-lms"
IMAGE_TAG="latest"
CONTAINER_NAME="bangla-app"
PORT="8080"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Functions

print_header() {
    echo -e "${BLUE}===================================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}===================================================${NC}"
}

print_success() {
    echo -e "${GREEN}✓ $1${NC}"
}

print_error() {
    echo -e "${RED}✗ $1${NC}"
}

print_info() {
    echo -e "${YELLOW}ℹ $1${NC}"
}

# Build Docker image
build_image() {
    print_header "Building Docker Image"
    
    if [ ! -f "$DOCKERFILE" ]; then
        print_error "$DOCKERFILE not found!"
        exit 1
    fi
    
    print_info "Building image: $IMAGE_NAME:$IMAGE_TAG"
    docker build -f "$DOCKERFILE" -t "$IMAGE_NAME:$IMAGE_TAG" .
    
    print_success "Image built successfully!"
    print_info "Image size: $(docker images | grep $IMAGE_NAME | awk '{print $7}')"
}

# Run Docker container
run_container() {
    print_header "Running Docker Container"
    
    # Stop existing container if running
    if docker ps | grep -q "$CONTAINER_NAME"; then
        print_info "Stopping existing container..."
        docker stop "$CONTAINER_NAME"
    fi
    
    # Remove existing container if exists
    if docker ps -a | grep -q "$CONTAINER_NAME"; then
        print_info "Removing existing container..."
        docker rm "$CONTAINER_NAME"
    fi
    
    print_info "Starting container: $CONTAINER_NAME"
    print_info "Mapping port: 0.0.0.0:$PORT -> 8080"
    
    docker run -d \
        --name "$CONTAINER_NAME" \
        -p "$PORT:8080" \
        -v bangla-data:/data \
        "$IMAGE_NAME:$IMAGE_TAG"
    
    print_success "Container started!"
    
    # Wait for container to be ready
    print_info "Waiting for application to start..."
    sleep 5
    
    # Show logs
    print_info "Recent logs:"
    docker logs "$CONTAINER_NAME" | tail -10
    
    print_success "Access your app at: http://localhost:$PORT"
}

# Stop container
stop_container() {
    print_header "Stopping Container"
    
    if docker ps | grep -q "$CONTAINER_NAME"; then
        print_info "Stopping $CONTAINER_NAME..."
        docker stop "$CONTAINER_NAME"
        print_success "Container stopped"
    else
        print_info "Container $CONTAINER_NAME is not running"
    fi
}

# View logs
view_logs() {
    print_header "Container Logs"
    
    if ! docker ps | grep -q "$CONTAINER_NAME"; then
        print_error "Container $CONTAINER_NAME is not running"
        exit 1
    fi
    
    docker logs -f "$CONTAINER_NAME"
}

# Open shell in container
open_shell() {
    print_header "Opening Shell in Container"
    
    if ! docker ps | grep -q "$CONTAINER_NAME"; then
        print_error "Container $CONTAINER_NAME is not running"
        exit 1
    fi
    
    print_info "Entering container shell (type 'exit' to quit)..."
    docker exec -it "$CONTAINER_NAME" sh
}

# Push to Docker Hub
push_image() {
    local registry="$1"
    
    if [ -z "$registry" ]; then
        print_error "Please provide Docker Hub username: ./docker-build.sh push <username>"
        exit 1
    fi
    
    print_header "Pushing to Docker Hub"
    
    print_info "Logging in to Docker..."
    docker login
    
    print_info "Tagging image: $IMAGE_NAME:$IMAGE_TAG -> $registry/$IMAGE_NAME:$IMAGE_TAG"
    docker tag "$IMAGE_NAME:$IMAGE_TAG" "$registry/$IMAGE_NAME:$IMAGE_TAG"
    
    print_info "Pushing image to Docker Hub..."
    docker push "$registry/$IMAGE_NAME:$IMAGE_TAG"
    
    print_success "Image pushed successfully!"
    print_info "You can now run: docker run -p 8080:8080 $registry/$IMAGE_NAME:$IMAGE_TAG"
}

# Clean up image and container
clean() {
    print_header "Cleaning Up"
    
    print_info "Stopping container..."
    stop_container
    
    if docker ps -a | grep -q "$CONTAINER_NAME"; then
        print_info "Removing container..."
        docker rm "$CONTAINER_NAME"
        print_success "Container removed"
    fi
    
    if docker images | grep -q "$IMAGE_NAME"; then
        print_info "Removing image..."
        docker rmi "$IMAGE_NAME:$IMAGE_TAG"
        print_success "Image removed"
    fi
    
    print_success "Cleanup complete"
}

# Test API
test_api() {
    print_header "Testing API"
    
    if ! docker ps | grep -q "$CONTAINER_NAME"; then
        print_error "Container $CONTAINER_NAME is not running"
        exit 1
    fi
    
    print_info "Testing endpoints..."
    
    # Test health
    print_info "Testing health endpoint..."
    if docker exec "$CONTAINER_NAME" wget -q -O- http://localhost:8080/api/courses > /dev/null 2>&1; then
        print_success "API is responding"
    else
        print_error "API is not responding"
        exit 1
    fi
    
    # Test static files
    print_info "Testing React frontend..."
    if docker exec "$CONTAINER_NAME" wget -q -O- http://localhost:8080 | grep -q "<!DOCTYPE"; then
        print_success "Frontend is serving"
    else
        print_error "Frontend is not serving"
        exit 1
    fi
    
    print_success "All tests passed!"
}

# Print usage
usage() {
    cat << EOF
${BLUE}Docker Build & Deploy Script for Bangla LMS${NC}

Usage: ./docker-build.sh [command] [options]

Commands:
  ${GREEN}build${NC}              - Build Docker image
  ${GREEN}run${NC}                - Build and run container
  ${GREEN}stop${NC}               - Stop running container
  ${GREEN}logs${NC}               - View container logs (live)
  ${GREEN}shell${NC}              - Open shell in container
  ${GREEN}push${NC} <username>    - Push to Docker Hub
  ${GREEN}test${NC}               - Test API endpoints
  ${GREEN}clean${NC}              - Remove image and container
  ${GREEN}help${NC}               - Show this help message

Examples:
  ./docker-build.sh build
  ./docker-build.sh run
  ./docker-build.sh logs
  ./docker-build.sh shell
  ./docker-build.sh push myusername
  ./docker-build.sh test
  ./docker-build.sh stop
  ./docker-build.sh clean

Environment Variables:
  DOCKERFILE        - Dockerfile to use (default: Dockerfile.monorepo)
  IMAGE_NAME        - Image name (default: bangla-lms)
  IMAGE_TAG         - Image tag (default: latest)
  CONTAINER_NAME    - Container name (default: bangla-app)
  PORT              - Port mapping (default: 8080)

Examples with env vars:
  PORT=9090 ./docker-build.sh run
  IMAGE_TAG=1.0.0 ./docker-build.sh build
  IMAGE_NAME=my-lms ./docker-build.sh run

EOF
}

# Main script logic

if [ $# -eq 0 ]; then
    usage
    exit 1
fi

case "$1" in
    build)
        build_image
        ;;
    run)
        build_image
        run_container
        ;;
    stop)
        stop_container
        ;;
    logs)
        view_logs
        ;;
    shell)
        open_shell
        ;;
    push)
        push_image "$2"
        ;;
    test)
        test_api
        ;;
    clean)
        clean
        ;;
    help)
        usage
        ;;
    *)
        print_error "Unknown command: $1"
        usage
        exit 1
        ;;
esac

exit 0
