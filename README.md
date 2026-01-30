# 🏧 ATM Interface – Java Web Application

A secure, real-world ATM simulation web application built using Core Java, Servlets, Hibernate (ORM), JSP, and MySQL.
This project demonstrates end-to-end backend development skills, including session management, transaction handling, database optimization, and clean layered architecture.

## 🚀 Features
-  🔐 PIN-based Authentication
-  💰 Balance Enquiry (Real-time from DB)
-  💵 Withdraw Money with Validation
-  💸 Fund Transfer Between Accounts
-  🔑 Change ATM PIN
-  🧾 Session-based User Handling
-  ⚡ Hibernate Second-Level Caching
-  🛡️ Input Validation & Error Handling
-  🎨 Modern UI using JSP & CSS


## 🛠️ Tech Stack

| Layer        | Technology                |
|--------------|---------------------------|
| Language     | Java                      |
| Backend      | Servlets                  |
| ORM          | Hibernate (JPA)           |
| Database     | MySQL                     |
| View         | JSP, HTML, CSS            |
| Server       | Apache Tomcat             |
| Architecture | MVC + Repository Pattern  |


## 📂 Project Structure
- src/main/java
- └── com.atmInterface
-    ├── config
-    │   └── DatabaseConfig.java
-    ├── entity
-    │   └── User.java
-    ├── repository
-    │   └── UserRepository.java
-    ├── repositoryImpl
-    │   └── UserRepositoryImpl.java
-    ├── BalanceEnquiryServlet.java
-    ├── ChangePinServlet.java
-    ├── DepositServlet.java
-    ├── PinServlet.java
-    └── WithdrawServlet.java
-
- src/main/resources
- └── hibernate.properties
-
- src/main/webapp
- ├── WEB-INF
- ├── index.jsp
- ├── menu.jsp
- ├── balanceEnquiry.jsp
- ├── deposit.jsp
- ├── withdraw.jsp
- └── changePin.jsp


## ✅ Clean Architecture
- Entity Layer → JPA-annotated User class
- Repository Layer → Interface-based abstraction
- Service Logic → Hibernate transactions
- Controller Layer → Servlets
- View Layer → JSP

## 🔐 Secure Session Management
- User authenticated via PIN
- Session timeout configured (5 minutes)
- Session validation on every operation
- Prevents unauthorized access

## 💾 Hibernate & Database Optimization
- Second-level caching enabled
- Indexed ATM PIN column for fast lookup
- Transaction-safe fund transfer

## 🛡️ Robust Validation
- Numeric-only PIN & Amount checks
- Minimum amount validation
- Insufficient balance protection
- Proper error messages on UI

## 🔁 Application Flow
- User enters PIN
- Session created
- Menu displayed
- User can:
  - Withdraw money
  Transfer funds
  - Check balance
  - Change PIN
  - Session invalidation on exit

## 🖥️ UI Preview
- Dark-themed ATM UI
- Responsive layouts
- Clear success/error feedback
- Real ATM-like user experience

## 👨‍💻 Developer
-  Umair Ali
- 🎓 B.Tech (CSE)
- 💻 Java Backend Developer
- 📚 Skills: Core Java, JDBC, Hibernate, Servlets, JSP, MySQL, SpringBoot, OOP, DSA, JavaScript, REST APIs, Spring Data JPA
