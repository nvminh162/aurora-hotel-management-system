cd /home/ubuntu/aurora-hotel-management-system/aurora-backend || exit

echo "Deploying branch production..."
git fetch -a
git checkout production
git pull origin production

docker compose up -d --pull always
docker system prune -af
