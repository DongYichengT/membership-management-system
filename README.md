# StarPavilion Membership Management System

## 📌 Project Overview
A modern membership management system built with front-end/back-end separation architecture, providing comprehensive solutions for "StarPavilion" including registration, points system, and privilege management. The system leverages Vue.js + Spring Boot technology stack to achieve efficient data interaction.

## ✨ Core Features
### Member Side
- 🔑 Multi-factor authentication (Username/Email)
- 🎁 Points accumulation & redemption (Consumption rewards/Event bonuses)
- 📊 Points history tracking (Detailed transaction records)
- 🏆 Tiered membership system (Bronze → Diamond)

### Admin Side
- 👥 CRUD operations for user management
- 🎉 Campaign configuration center (Discounts/Double points/Special offers)
- 📈 Tier configuration (Points thresholds/Privilege settings)
- 📊 Data dashboard (Membership growth/Points flow)

## 🛠️ Technology Stack
| Category       | Technologies                                                                 |
|----------------|----------------------------------------------------------------------------|
| Frontend       | Vue 3 + Composition API + Pinia + Element Plus + ECharts                   |
| Backend        | Spring Boot 3 + Spring Security + MyBatis-Plus                             |
| Database       | MySQL 8.0 + Redis Cache                                                    |
| Deployment     | Nginx Reverse Proxy + Docker Containerization                             |
| API Style      | RESTful API with JWT Authentication                                        |

## 🚀 Quick Start
### Frontend Setup
```bash
# Install dependencies
npm install

# Development mode
npm run dev

# Build production package
npm run build

## Backend Configuration
# application.yml example
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/star_member?useSSL=false
    username: root
    password: 123456
  redis:
    host: localhost
    port: 6379

mybatis-plus:
  mapper-locations: classpath:mapper/**/*.xml
#🔌 API Documentation
## User Registration

