# SalesSavvy - Online Sales Management System

SalesSavvy is a full-stack web application designed for seamless online sales management, providing an intuitive shopping experience, secure payment processing, and efficient inventory tracking.

## 🚀 Features

- **User Authentication & Management** - Secure login, registration, and role-based access control.  
- **Shopping & Order Management** - Customers can browse products, add to cart, and track orders.  
- **Secure Payment Integration** - Supports Razorpay/Stripe for online transactions.  
- **Admin Dashboard** - Manage products, orders, and user accounts.  
- **Responsive UI** - Built with ReactJS and optimized for mobile and desktop.   

## 🛠️ Tech Stack

- **Frontend:** React (Vite), HTML, CSS, JavaScript  
- **Backend:** Java Spring Boot (REST API)  
- **Database:** MySQL  
- **Payment Gateway:** Razorpay / Stripe   
- **Version Control:** Git & GitHub  

## 📂 Project Structure

```bash
SalesSavvy/
│── backend/          # Spring Boot API
│── frontend/         # ReactJS UI
│── database/         # MySQL database setup
│── README.md         # Project documentation
```

## 🚀 Getting Started

### Prerequisites
Ensure you have the following installed:
-  **npm** (for React frontend)
- **Java 17+** (for Spring Boot backend)
- **MySQL** (for database)


### 🔧 Installation & Setup

#### 1️⃣ Clone the Repository
```bash
git clone https://github.com/yourusername/SalesSavvy.git
cd SalesSavvy
```

#### 2️⃣ Backend Setup (Spring Boot)
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### 3️⃣ Frontend Setup (ReactJS)
```bash
cd frontend
npm install
npm run dev
```

#### 4️⃣ Database Setup (MySQL)
```sql
CREATE DATABASE *DataBaseName*;
```

#### 5️⃣ Run with Docker
```bash
docker-compose up --build
```

## 🎯Some basic API Endpoints (Sample)
| Endpoint               | Method | Description         |
|------------------------|--------|---------------------|
| `/api/auth/register`   | POST   | Register a new user |
| `/api/auth/login`      | POST   | User login         |
| `/api/products`        | GET    | Get all products   |
| `/api/orders`          | POST   | Create an order    |

## 📸 Screenshots

![Home Page]![image](https://github.com/user-attachments/assets/4f4328f9-ccb8-4131-8257-d04f068e7b59)
  
*Home Page of SalesSavvy*

![Checkout]![image](https://github.com/user-attachments/assets/7f748be7-7e92-4a4b-a33f-b7c6d4b5faeb)
  
*Checkout Process*

## 📌 Future Enhancements
- Add **GraphQL** for optimized API calls.
- Implement **CI/CD pipelines** for automated deployments.
- Improve **caching and performance** using Redis.

## 🤝 Contributing
Contributions are welcome! Feel free to submit a pull request.

## 📜 License
This project is licensed under the **MIT License**.

---
🚀
