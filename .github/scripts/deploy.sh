#!/bin/bash

# Ensure we are in the application directory
cd ${APP_DIR:-/app/aurora-hotel-management-system}

echo "Starting deployment..."

# Pull latest code
git fetch origin master
git checkout master
git pull origin master

# Login to Docker Hub (if needed for private images)
echo "${DOCKERHUB_PASSWORD}" | docker login -u "${DOCKERHUB_USERNAME}" --password-stdin

# Pull latest images
echo "Pulling latest images..."
docker compose pull aurora-backend aurora-frontend

# Restart services
echo "Restarting services..."
docker compose up -d

# Clean up unused images
echo "Cleaning up..."
docker system prune -f

echo "Deployment completed successfully!"