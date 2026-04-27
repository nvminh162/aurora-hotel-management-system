BRANCH=$(git rev-parse --abbrev-ref HEAD)

echo "Deploying with branch: $BRANCH"

# Navigate to the project directory
cd ~/aurora-hotel-management-system/
git fetch -a
git checkout $BRANCH
git pull

docker compose -f docker-compose.yml down
# Build/Pull and start the backend service
docker compose -f docker-compose.yml pull backend
docker compose -f docker-compose.yml up -d backend
docker system prune -af