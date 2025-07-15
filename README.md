# 🍔 Food Delivery System (Core Java Project)

A **console-based Food Delivery System** built using **Core Java**. It allows customers to browse restaurants, view menus, place orders, make payments via wallet or cash, and submit ratings. Admins can manage restaurants, menu items, view all orders, and see sales analytics.

---

## 📌 Features

### 👤 User Authentication
- Register/Login as **Customer** or **Admin**
- Role-based access and features

### 🏪 Restaurant Management (Admin)
- Add/View Restaurants
- Add/View Menu Items
- Menu stored using per-restaurant `.txt` files

### 🍽️ Customer Functionality
- Browse restaurants & menus
- Place orders (Wallet or COD)
- Cancel orders
- View past orders
- Search menu by name
- Filter menu by price
- Top-up wallet balance

### 📦 Order Management
- Admin can view all orders
- Update order status: PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
- Cancel order with refund logic for wallet payments

### ⭐ Ratings & Reviews
- Customers can add ratings & comments after order
- Restaurant average rating calculation

### 📊 Admin Reports
- Total orders & revenue
- Top 3 restaurants by order count
- Top 3 most-ordered items
- Most active customers

---

## 🧑‍💻 Tech Stack

- **Language:** Java (JDK 11+)
- **Architecture:** Core Java OOP (no frameworks)
- **Persistence:** File I/O (`.txt`)
- **Design Patterns:** Service Layer, Separation of Concerns

---

## 🚀 Run the Project

### 💡 Option 1: Compile and Run
```bash
javac -d out $(find . -name "*.java")
java -cp out fooddelivery.testing.MainApp
