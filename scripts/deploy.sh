#!/bin/bash
# Stop on error
set -e

# PHẢI là đường dẫn vào thư mục dự án, không phải thư mục /home/ubuntu
PROJECT_DIR="/home/ubuntu/aurora-hotel-management-system"
REPO_URL="git@github.com:nvminh162/aurora-hotel-management-system.git"

# 1. Kiểm tra và Clone nếu chưa có
if [ ! -d "$PROJECT_DIR" ]; then
    echo "Dự án chưa tồn tại tại $PROJECT_DIR. Đang tiến hành clone..."
    git clone "$REPO_URL" "$PROJECT_DIR"
fi

cd "$PROJECT_DIR"
echo "Đã vào thư mục: $(pwd)"

# 2. Cập nhật code từ GitHub
echo "Đang lấy code mới nhất..."
git fetch origin master
git reset --hard origin/master

# 3. Khởi động lại dịch vụ backend
echo "Đang cập nhật Docker Image và khởi động lại backend..."
docker compose pull backend
docker compose up -d backend

# 4. Dọn dẹp tài nguyên thừa
docker system prune -f

echo "Deploy thành công!"