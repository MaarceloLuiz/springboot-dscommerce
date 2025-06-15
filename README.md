# Full Stack E-Commerce Application
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/MaarceloLuiz/springboot-dscommerce/blob/main/LICENSE) 

## Objective
This full-stack E-Commerce application features a robust backend built with Spring Boot (Java, Maven), utilizing JWT (JSON Web Tokens) for secure authentication and authorization.

## Features
- User Management
  - Register new users
  - Login with authentication
  - Role-based authorization (Client and Admin)
  - Admin can manage users
- Product & Category Management
  - CRUD operations for products and categories (Admin only)
  - Paginated product catalog
  - Product filtering by name
- Shopping Cart & Order Processing
  - Add and remove products from the cart
  - Update item quantities in the cart
  - Save orders with status tracking
  - View order history (clients)
  - Admin can manage and report orders
- Security & Error Handling
  - JWT-based authentication
  - Exception handling for:
    - Resource not found (404)
    - Validation errors (422)
    - Unauthorized access (403)

## Domain Model
## **Domain Model**
- **User**: Contains fields for `id`, `name`, `email`, `phone`, `birthDate`, `password`, and `roles`. A user can place multiple orders.

- **Product**: Represents a product in the system with fields `id`, `name`, `description`, `price`, and `imgUrl`. A product belongs to one or more categories.

- **Category**: Represents product categories, containing `id` and `name`. A category can have multiple products.

- **Order**: Represents a purchase made by a user. Fields include `id`, `moment` (timestamp of order), and `status` (defined by the `OrderStatus` enum). A user can have multiple orders.

- **OrderItem**: Represents an item within an order, containing `quantity` and `price`. Links products to orders.

- **Payment**: Represents a payment made for an order. Contains `id` and `moment`. Each order can have at most one payment.

- **OrderStatus**: An enumeration defining order statuses:
  - `WAITING_PAYMENT`
  - `PAID`
  - `SHIPPED`
  - `DELIVERED`
  - `CANCELED`

---

![dscommercedomainmodel](https://github.com/user-attachments/assets/4830f360-92c8-4269-b550-775a81781c7c)

---

## Tech && Frameworks
- **Spring Boot**: Framework for building the REST API.
- **JPA/Hibernate**: ORM for database management.
- **H2 Database**: In-memory database for testing.
- **JWT Authentication**: Secure login & authorization
- **Postman**: For testing the API endpoints.
- **Maven**: Build automation.
- **Yarn**: Frontend package management

## How to Run
## First Option (Recommended):
### Dockerfile
### Steps
#### 1. Clone the repository:
```bash
git clone https://github.com/MaarceloLuiz/springboot-dscommerce.git
cd DSCommerce
```
- Choose a method to start the application
  - You can run the app in two ways:
    - Simply using Docker-Compose
    - Using detached Docker-Compose

#### 2. Option 1: Using Docker-Compose:
- `docker-compose up`

#### 3. To stop
- CTRL + C

#### 2. Option 2: Detached Docker-Compose:
- `docker-compose up -d`

#### 3. To stop
- `docker-compose down`

---

## Second Option:
### Command Line:
### Prerequisites
- Java 17 or higher
- Maven
- Node.js and Yarn

#### Check Installed Versions:
```bash
java -version
mvn -version
yarn -v
```
If any command fails, install the missing dependency before proceeding.

### Steps
#### 1. Clone the repository:
```bash
git clone https://github.com/MaarceloLuiz/springboot-dscommerce.git
cd DSCommerce
```
- Choose a method to start the application
  - You can run the app in two ways:
    - Using the Bash script (run.sh)
    - Using a single command

#### 2. Option 1: Using the Bash Script
```bash
chmod +x run.sh
./run.sh
```

#### 2. Option 2: Using a Single Command
```bash
(cd backend && mvn clean install && mvn spring-boot:run) & (cd frontend && yarn install && yarn dev)
```

#### 3. Accessing the Application
```bash
http://localhost:5173
```

#### 4. Open Postman (or any API testing tool) and use the following base URL to access the API endpoints

- Base URL:
```bash
http://localhost:8080
```

- The available endpoints include the following:

---

| Method | Endpoint               | Description                          | Access  |
|--------|------------------------|--------------------------------------|---------|
| POST   | `/oauth2/token`        | Authenticate user and generate JWT Bearer Token | All  |
| GET    | `/users/me`            | Retrieve information of the logged-in user | Admin, Client  |
| GET    | `/products`            | List paginated products              | Public  |
| GET    | `/products/{id}`       | Get product details by ID            | Public  |
| POST   | `/products`            | Add new product                      | Admin   |
| PUT    | `/products/{id}`       | Update product by ID                 | Admin   |
| DELETE | `/products/{id}`       | Delete product by ID                 | Admin   |
| GET    | `/categories`          | List product categories              | Public  |
| GET    | `/orders`              | View user orders (client)            | Client  |
| POST   | `/orders`              | Create a new order                   | Client  |

---

- For example, you can test the GET /products endpoint in Postman by sending a request to:
```bash
http://localhost:8080/products
```

#### 5. To stop the application, simply press the following in the terminal:
```bash
Ctrl + C
```

- If port 8080 is still in use after pressing Ctrl + C, check and kill the process manually to release it.

## Author
Marcelo Luiz Guimarães Pereira

<a href="https://www.linkedin.com/in/marcelo-luiz-guimar%C3%A3es-pereira-613933269/"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/></a>

---
