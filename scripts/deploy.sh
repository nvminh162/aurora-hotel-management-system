#!/bin/bash

# Configuration
PROJECT_DIR="/home/ubuntu/aurora-hotel-management-system"
echo "Starting deployment in $PROJECT_DIR..."

# Check if project directory exists
if [ ! -d "$PROJECT_DIR" ]; then
    echo "Error: Directory $PROJECT_DIR not found."
    exit 1
fi

cd "$PROJECT_DIR" || exit 1

# Pull latest changes from git (optional if using Docker Hub images mostly)
echo "Pulling latest code..."
git pull origin master

# Pull the latest backend image from Docker Hub
echo "Pulling latest backend image..."
docker compose pull backend

# Restart the backend service
echo "Restarting backend service..."
docker compose up -d backend

# Optional: restart other essential services if they are not running
# docker compose up -d rag_postgres redis

# Clean up old images
echo "Cleaning up old images..."
docker image prune -f

echo "Deployment completed successfully!"