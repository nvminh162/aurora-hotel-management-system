PROJECT_DIR="/home/ubuntu/project"
REPO_URL="git@github.com:nvminh162/aurora-hotel-management-system.git"

# Check if project directory exists, if not clone it via SSH
if [ ! -d "$PROJECT_DIR" ]; then
    echo "Directory $PROJECT_DIR not found. Cloning private repository via SSH..."
    git clone "$REPO_URL" "$PROJECT_DIR"
fi

cd "$PROJECT_DIR" || { echo "Failed to enter $PROJECT_DIR"; exit 1; }

# Get current branch
BRANCH=$(git rev-parse --abbrev-ref HEAD)
echo "Deploying with branch: $BRANCH"

git fetch -a
git checkout $BRANCH
git pull

docker compose -f docker-compose.yml down
# Build/Pull and start the backend service
docker compose -f docker-compose.yml pull backend
docker compose -f docker-compose.yml up -d backend
docker system prune -af