cd /home/ubuntu/aurora-hotel-management-system/aurora-backend || exit

echo "Deploying branch master..."
git fetch -a
git checkout master
git pull origin master

docker compose up -d --pull always
docker system prune -af
