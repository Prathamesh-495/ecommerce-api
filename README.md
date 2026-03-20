# 🛒 E-Commerce REST API

A production-ready E-Commerce REST API built with **Spring Boot**, featuring JWT authentication, cart management, order processing, and Docker containerization.

## 🚀 Tech Stack

| Technology | Purpose |
|---|---|
| Spring Boot 4 | Backend Framework |
| Spring Security + JWT | Authentication & Authorization |
| Spring Data JPA + Hibernate | ORM & Database Layer |
| MySQL 8 | Relational Database |
| Docker + Docker Compose | Containerization |
| Maven | Build Tool |
| Lombok | Boilerplate Reduction |

## ✨ Features

- ✅ JWT Authentication (Register/Login)
- ✅ Role-based Authorization (ADMIN/USER)
- ✅ Product & Category CRUD
- ✅ Cart Management
- ✅ Order Placement with stock management
- ✅ Password encryption with BCrypt
- ✅ Dockerized with health checks

## 📌 API Endpoints

### Auth
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | /api/auth/register | Register user | No |
| POST | /api/auth/login | Login & get token | No |

### Categories
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | /api/categories/ | Get all categories | Yes |
| GET | /api/categories/{id} | Get category by ID | Yes |
| POST | /api/categories/ | Create category | Yes |
| PUT | /api/categories/{id} | Update category | Yes |
| DELETE | /api/categories/{id} | Delete category | Yes |

### Products
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | /api/products/ | Get all products | Yes |
| GET | /api/products/{id} | Get product by ID | Yes |
| POST | /api/products/ | Create product | Yes |
| PUT | /api/products/{id} | Update product | Yes |
| DELETE | /api/products/{id} | Delete product | Yes |

### Cart
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | /api/cart/ | View cart | Yes |
| POST | /api/cart/add | Add to cart | Yes |
| DELETE | /api/cart/remove/{id} | Remove item | Yes |
| DELETE | /api/cart/clear | Clear cart | Yes |

### Orders
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | /api/orders/place | Place order | Yes |
| GET | /api/orders/history | Order history | Yes |

## 🗄️ Database Design
```
users ──────────< orders ──────────< order_items >────── products
                                                              ↑
categories ────────────────────────────────────────────────────┘
users ──────────< cart_items >────── products
```

## ⚙️ Setup & Run Locally

### Prerequisites
- Java 23
- MySQL 8
- Maven

### Steps
```bash
# Clone the repo
git clone https://github.com/Prathamesh-495/ecommerce-api.git
cd ecommerce-api

# Configure database in src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# Run
./mvnw spring-boot:run
```

## 🐳 Run with Docker
```bash
# Clone the repo
git clone https://github.com/Prathamesh-495/ecommerce-api.git
cd ecommerce-api

# Run with Docker Compose
docker-compose up --build
```

App runs on `http://localhost:8080` 🚀

## 🔐 Authentication

All protected endpoints require a Bearer token in the header:
```
Authorization: Bearer <your_jwt_token>
```

Get your token by calling `/api/auth/login` first.

## 👨‍💻 Author

**Prathamesh Pande**  
[GitHub](https://github.com/Prathamesh-495)