cd /home/ubuntu/aurora-hotel-management-system || exit

echo "Deploying branch master..."
git fetch -a
git checkout master
git pull origin master

docker compose -f docker-compose.yml pull aurora-backend aurora-frontend
docker compose -f docker-compose.yml up -d aurora-backend aurora-frontend
docker system prune -af