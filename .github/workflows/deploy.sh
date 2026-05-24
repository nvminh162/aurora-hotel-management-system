cd /home/ubuntu/aurora-hotel-management-system/aurora-backend || exit

echo "Deploying branch master..."
git fetch -a
git checkout master
git pull origin master

docker compose -f docker-compose.yml pull aurora-backend
docker compose -f docker-compose.yml up -d aurora-backend
docker system prune -af
