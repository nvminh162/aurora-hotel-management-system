<!-- ========================================== -->
<!-- 🌌 AURORA HOTEL MANAGEMENT SYSTEM README -->
<!-- ========================================== -->
 
<div align="center">

<!-- Logo & Title -->
<img src="aurora-frontend/src/assets/images/commons/aurora-logo.png" alt="Aurora Hotel" width="180" style="border-radius: 20px;"/>

# ✨ Aurora Hotel Management System ✨

<p>
<strong>A modern, full-featured hotel management platform powered by AI</strong>
</p>

<!-- Quick Links -->
[Features](#-features) • [Tech Stack](#-tech-stack) • [Getting Started](#-getting-started) • [API](#-api-reference) • [Team](#-team)

<!-- Main Tech Badges -->
<p>
<img src="https://img.shields.io/badge/Spring_Boot-3.5.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
<img src="https://img.shields.io/badge/React-19-61DAFB?style=for-the-badge&logo=react&logoColor=black"/>
<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/TypeScript-5.9-3178C6?style=for-the-badge&logo=typescript&logoColor=white"/>
<img src="https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
</p>

<!-- Secondary Badges -->
<p>
<img src="https://img.shields.io/badge/Vite-7.1-646CFF?style=flat-square&logo=vite&logoColor=white"/>
<img src="https://img.shields.io/badge/Tailwind-4.1-06B6D4?style=flat-square&logo=tailwindcss&logoColor=white"/>
<img src="https://img.shields.io/badge/Redis-7.2-DC382D?style=flat-square&logo=redis&logoColor=white"/>
<img src="https://img.shields.io/badge/Docker-Ready-2496ED?style=flat-square&logo=docker&logoColor=white"/>
<img src="https://img.shields.io/badge/AI_Powered-Gemini-8E75B2?style=flat-square&logo=google&logoColor=white"/>
</p>

<!-- Repo Stats -->
<p>
<img src="https://img.shields.io/github/stars/giasinguyen/aurora-hotel-management-system?style=flat-square&color=yellow"/>
<img src="https://img.shields.io/github/forks/giasinguyen/aurora-hotel-management-system?style=flat-square&color=blue"/>
<img src="https://img.shields.io/github/issues/giasinguyen/aurora-hotel-management-system?style=flat-square&color=red"/>
<img src="https://img.shields.io/github/license/giasinguyen/aurora-hotel-management-system?style=flat-square&color=green"/>
</p>

</div>

---

## 📖 About

**Aurora Hotel Management System** is an enterprise-grade hotel management solution developed as a capstone project for the **Web Programming (WWW)** course at **Industrial University of Ho Chi Minh City (IUH)**.

<div align="center">

| 🤖 AI-Powered | 🔐 Secure | 🌍 Multi-language | 📱 Responsive |
|:---:|:---:|:---:|:---:|
| RAG Chatbot with Google Gemini | RBAC with 70+ permissions | Vietnamese & English | Modern UI/UX |

</div>

---

## 🚀 Features

<table>
<tr>
<td width="50%" valign="top">

### 🏨 Hotel Operations
- **Multi-branch Management** — Centralized control for multiple locations
- **Room Management** — Categories, types, amenities & real-time status
- **Booking System** — Complete workflow from reservation to checkout
- **Service Booking** — Spa, dining, airport transfer & more

</td>
<td width="50%" valign="top">

### 💼 Business Tools
- **Revenue Dashboard** — Real-time analytics & insights
- **Staff Scheduling** — Shift management & attendance tracking
- **Promotions Engine** — Flexible discount & coupon system
- **Report Generation** — Export to PDF/Excel

</td>
</tr>
<tr>
<td width="50%" valign="top">

### 🔐 Security & Access
- **Role-Based Access Control** — Admin, Manager, Staff, Customer, Guest
- **70+ Granular Permissions** — Fine-tuned access control
- **JWT Authentication** — Secure token-based auth
- **OAuth2 Integration** — Social login support

</td>
<td width="50%" valign="top">

### 🤖 AI Features
- **RAG Chatbot** — Intelligent Q&A powered by Google Gemini
- **Semantic Search** — Vector similarity search with pgvector
- **Document Intelligence** — Auto-parse & understand documents
- **Streaming Responses** — Real-time AI interactions

</td>
</tr>
</table>

### 🧠 AI Architecture

```
╔══════════════════════════════════════════════════════════════════╗
║                      RAG CHATBOT PIPELINE                        ║
╠══════════════════════════════════════════════════════════════════╣
║                                                                  ║
║   📄 Documents      🔄 Processing       🧮 Embeddings            ║
║   ┌─────────┐      ┌─────────────┐     ┌──────────────┐         ║
║   │  PDF    │ ───▶ │ Apache Tika │ ──▶ │ Google       │         ║
║   │  DOCX   │      │ Langchain4j │     │ Gemini AI    │         ║
║   │  TXT    │      └─────────────┘     └──────┬───────┘         ║
║   └─────────┘                                 │                  ║
║                                               ▼                  ║
║                        ┌──────────────────────────────┐         ║
║                        │     🗄️ pgvector Database     │         ║
║                        │   Vector Similarity Search   │         ║
║                        └──────────────┬───────────────┘         ║
║                                       │                          ║
║   💬 User Query ──▶ Semantic Match ──▶ Context + LLM ──▶ 🎯     ║
║                                                                  ║
╚══════════════════════════════════════════════════════════════════╝
```

---

## 🛠 Tech Stack

<table>
<tr>
<th align="center">🖥️ Backend</th>
<th align="center">🎨 Frontend</th>
<th align="center">🗄️ Data & AI</th>
<th align="center">🔧 DevOps</th>
</tr>
<tr>
<td valign="top">

**Framework**
- Java 21 LTS
- Spring Boot 3.5.5
- Spring Security 6.x
- Spring Data JPA

**Libraries**
- Langchain4j 1.7.1
- MapStruct 1.6.3
- Lombok 1.18.36
- Nimbus JWT 9.40

</td>
<td valign="top">

**Framework**
- React 19.1.1
- TypeScript 5.9.3
- Vite 7.1.7

**UI/UX**
- TailwindCSS 4.1.14
- Framer Motion 12.x
- Radix UI
- Recharts 3.5.1

**State**
- Redux Toolkit 2.9.0
- React Router 7.9.4

</td>
<td valign="top">

**Database**
- PostgreSQL 16
- pgvector 0.1.6
- Redis 7.2

**AI/ML**
- Google Gemini AI
- RAG Architecture
- Vector Embeddings
- Apache Tika

</td>
<td valign="top">

**Infrastructure**
- Docker
- Railway
- Nginx

**Services**
- Cloudinary
- VNPay
- Mailjet

**Tools**
- Postman
- pgAdmin 4

</td>
</tr>
</table>

---

## 📐 Architecture

```
┌─────────────────────────────────────────────────────────────────────────┐
│                            🌐 CLIENT TIER                               │
│    ┌────────────┐    ┌────────────┐    ┌────────────┐                  │
│    │  Browser   │    │   Mobile   │    │  Postman   │                  │
│    │  (React)   │    │  (Future)  │    │  (Testing) │                  │
│    └─────┬──────┘    └─────┬──────┘    └─────┬──────┘                  │
└──────────┼─────────────────┼─────────────────┼──────────────────────────┘
           │                 │                 │
           └────────────────┬┴─────────────────┘
                            │ REST API / HTTPS
┌───────────────────────────┼─────────────────────────────────────────────┐
│                           ▼         🛡️ SECURITY                        │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │   JWT Filter  →  CORS  →  Rate Limiter  →  Permission AOP     │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                         │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │                    📡 API LAYER (31 Controllers)               │    │
│  │   Auth • User • Branch • Room • Booking • Payment • RAG ...   │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                         │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │                    ⚙️ SERVICE LAYER                            │    │
│  │          Business Logic • Validation • Transactions            │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                         │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │                    💾 REPOSITORY LAYER                         │    │
│  │       JPA Repositories • Custom Queries • Specifications       │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                         ☕ SPRING BOOT APPLICATION                      │
└─────────────────────────────────────────────────────────────────────────┘
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐   ┌───────────────┐   ┌───────────────┐
   │  PostgreSQL   │   │     Redis     │   │  Cloudinary   │
   │  + pgvector   │   │  Cache/Queue  │   │    Storage    │
   └───────────────┘   └───────────────┘   └───────────────┘
```

### 📊 Data Model Overview

<table>
<tr>
<td width="25%">

**👤 Users & Auth**
- User
- Role  
- Permission
- SessionMeta
- InvalidatedToken

</td>
<td width="25%">

**🏨 Hotel Core**
- Branch
- Room
- RoomType
- RoomCategory
- Amenity / Facility

</td>
<td width="25%">

**📅 Operations**
- Booking
- BookingRoom
- Payment
- Service
- ServiceBooking

</td>
<td width="25%">

**📰 Content & Staff**
- News
- Promotion
- Document
- WorkShift
- ShiftCheckIn

</td>
</tr>
</table>

> **29 Entity Classes** • **31 REST Controllers** • **70+ Permissions**

---

## 🚀 Getting Started

### Prerequisites

| Tool | Version | Required |
|------|---------|:--------:|
| Java JDK | 21+ | ✅ |
| Node.js | 20+ | ✅ |
| Docker | Latest | Recommended |
| PostgreSQL | 16+ | If no Docker |
| Redis | 7+ | If no Docker |

### ⚡ Quick Start with Docker

```bash
# Clone the repository
git clone https://github.com/giasinguyen/aurora-hotel-management-system.git
cd aurora-hotel-management-system

# Start infrastructure services
cd aurora-backend && docker-compose up -d

# Services available at:
# • PostgreSQL: localhost:5432
# • Redis: localhost:6379  
# • pgAdmin: localhost:5050
```

### 🔧 Backend Setup

```bash
cd aurora-backend

# Create environment file
cp .env.example .env

# Configure your .env file (see below)

# Run the application
./mvnw spring-boot:run
```

<details>
<summary>📋 <strong>Environment Variables</strong></summary>

```env
# Database
DB_URL=jdbc:postgresql://localhost:5432/aurora_hotel
DB_USERNAME=admin
DB_PASSWORD=admin

# JWT (generate a 64+ char secret)
JWT_SIGNER_KEY=your-super-secret-key-minimum-64-characters-for-hs512
JWT_VALID_DURATION=3600
JWT_REFRESHABLE_DURATION=86400

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=admin

# Google Gemini AI
GEMINI_API_KEY=your-gemini-api-key

# Cloudinary
CLOUDINARY_CLOUD_NAME=your-cloud-name
CLOUDINARY_API_KEY=your-api-key
CLOUDINARY_API_SECRET=your-api-secret

# VNPay
VNPAY_TMN_CODE=your-tmn-code
VNPAY_HASH_SECRET=your-hash-secret
```

</details>

### 🎨 Frontend Setup

```bash
cd aurora-frontend

# Install dependencies
npm install

# Create environment file
cp .env.example .env.local

# Configure .env.local
echo "VITE_API_BASE_URL=http://localhost:8080" > .env.local

# Start development server
npm run dev
```

### 📦 Production Build

```bash
# Backend
cd aurora-backend
./mvnw clean package -DskipTests
java -jar target/*.jar

# Frontend  
cd aurora-frontend
npm run build && npm run preview
```

---

## 📚 API Reference

### 🔐 Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/auth/token` | Login & get JWT token |
| `POST` | `/api/v1/auth/refresh` | Refresh access token |
| `POST` | `/api/v1/auth/logout` | Logout & invalidate token |
| `POST` | `/api/v1/auth/introspect` | Validate token |

### 👥 Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/users` | List all users |
| `GET` | `/api/v1/users/{id}` | Get user by ID |
| `POST` | `/api/v1/users` | Create new user |
| `PUT` | `/api/v1/users/{id}` | Update user |
| `DELETE` | `/api/v1/users/{id}` | Delete user |

### 🏨 Hotel

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/branches` | List branches |
| `GET` | `/api/v1/rooms` | List rooms |
| `GET` | `/api/v1/room-types` | List room types |
| `GET` | `/api/v1/amenities` | List amenities |

### 📅 Bookings

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/bookings` | Create booking |
| `GET` | `/api/v1/bookings/{id}` | Get booking details |
| `PUT` | `/api/v1/bookings/{id}/confirm` | Confirm booking |
| `PUT` | `/api/v1/bookings/{id}/check-in` | Check-in |
| `PUT` | `/api/v1/bookings/{id}/check-out` | Check-out |

### 🤖 AI Chatbot

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/rag/chat` | Chat with AI |
| `POST` | `/api/v1/rag/chat-stream` | Streaming chat |
| `POST` | `/api/v1/rag/documents` | Upload document |

<details>
<summary>📬 <strong>Postman Collections</strong></summary>

Import these files for API testing:
- `postman/Aurora Hotel Management System.postman_collection.json`
- `postman/Aurora_Hotel_Authentication_Tests.postman_collection.json`

</details>

---

## 📁 Project Structure

```
📦 aurora-hotel-management-system
├── 📂 aurora-backend/                 # Spring Boot API
│   ├── 📂 src/main/java/.../
│   │   ├── 📂 config/                 # App configurations
│   │   ├── 📂 controller/             # REST endpoints (31)
│   │   ├── 📂 dto/                    # Data transfer objects
│   │   ├── 📂 entity/                 # JPA entities (29)
│   │   ├── 📂 repository/             # Data access layer
│   │   ├── 📂 service/                # Business logic
│   │   └── 📂 security/               # JWT & auth
│   ├── 📂 postman/                    # API collections
│   ├── 📄 docker-compose.yml
│   └── 📄 pom.xml
│
├── 📂 aurora-frontend/                # React SPA
│   ├── 📂 src/
│   │   ├── 📂 components/             # UI components
│   │   ├── 📂 pages/                  # Page views (17 modules)
│   │   ├── 📂 features/               # Redux slices
│   │   ├── 📂 services/               # API clients
│   │   ├── 📂 hooks/                  # Custom hooks
│   │   └── 📂 locales/                # i18n files
│   ├── 📄 package.json
│   └── 📄 vite.config.ts
│
├── 📂 docs/                           # Documentation
│   ├── 📄 class-diagram.puml
│   ├── 📄 database-diagram.puml
│   └── 📄 database-schema.sql
│
└── 📄 README.md
```

---

## 👥 Team

<div align="center">

<table>
<tr>
<td align="center">
<a href="https://github.com/giasinguyen">
<img src="https://github.com/giasinguyen.png" width="80px;" style="border-radius:50%;" alt=""/><br/>
<sub><b>Nguyễn Trần Gia Sĩ</b></sub>
</a><br/>
</td>
<td align="center">
<a href="https://github.com/nvminh162">
<img src="https://github.com/nvminh162.png" width="80px;" style="border-radius:50%;" alt=""/><br/>
<sub><b>Nguyễn Văn Minh</b></sub>
</a><br/>
</td>
<td align="center">
<a href="https://github.com/NguyenNguyen0">
<img src="https://github.com/NguyenNguyen0.png" width="80px;" style="border-radius:50%;" alt=""/><br/>
<sub><b>Nguyễn Trung Nguyên</b></sub>
</a><br/>
</td>
<td align="center">
<a href="https://github.com/NguyenDuyKhai2">
<img src="https://github.com/NguyenDuyKhai2.png" width="80px;" style="border-radius:50%;" alt=""/><br/>
<sub><b>Nguyễn Duy Khải</b></sub>
</a><br/>
</td>
</tr>
</table>

</div>

---

## 📚 Documentation

| Document | Description |
|----------|-------------|
| [Frontend Guide](./aurora-frontend/README.md) | React setup, components & styling |
| [Backend Guide](./aurora-backend/README.md) | Spring Boot setup & API reference |
| [System Diagrams](./docs/README.md) | Architecture & database diagrams |

---

## 📄 License

<div align="center">

This project was developed for educational purposes as part of the  
**Web Programming (WWW)** course at  
**Industrial University of Ho Chi Minh City (IUH)**

**© 2024-2025 Aurora Development Team**

</div>

---

## 🙏 Acknowledgments

<div align="center">

**Industrial University of Ho Chi Minh City** — Faculty of Information Technology  
**Course Instructors** — Web Programming (WWW)  
**Open Source Community** — Spring, React, PostgreSQL & all contributors

</div>

---

<div align="center">

### 💫 If you found this helpful, please give us a ⭐

<br/>

<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=6,11,20&height=100&section=footer&animation=twinkling"/>

**Built with ❤️ by The Challengers Team**

[Report Bug](https://github.com/giasinguyen/aurora-hotel-management-system/issues) • [Request Feature](https://github.com/giasinguyen/aurora-hotel-management-system/issues)

</div>


