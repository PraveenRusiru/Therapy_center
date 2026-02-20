# Therapy Center Management System (JavaFX)

A **desktop-based Therapy Center Management System** built with **JavaFX** and **Hibernate**, designed to help a therapy center manage day-to-day operations (clients, sessions/appointments, records, payments, and reporting) in a clean and structured way.

🔗 **GitHub Repo:** https://github.com/PraveenRusiru/Therapy_center

---

## ✨ Key Highlights

- **JavaFX UI** (with FXML) for a modern desktop experience  
- **Hibernate + JPA** for database persistence  
- **MySQL** as the relational database  
- **Reports support** with JasperReports  
- **Email support** (Java Mail) for notifications/communication  
- **Password hashing** with jBCrypt  

> Note: Update the feature list with your exact modules/screens if you want it to be 100% feature-accurate.

---

## 🧰 Tech Stack

- **Language:** Java (JDK 17)
- **UI:** JavaFX (Controls + FXML), JFoenix  
- **ORM / Persistence:** Hibernate Core, Jakarta Persistence (JPA)  
- **Database:** MySQL Connector/J  
- **Utilities:** Lombok  
- **Reporting:** JasperReports  
- **Email:** Java Mail (javax.mail)  
- **Security:** jBCrypt  
- **Build Tool:** Maven (Maven Wrapper included)

---

## 📁 Project Structure (Typical)

> The repository follows the standard Maven layout.
>
> Therapy_center/
├─ .mvn/                  # Maven wrapper files
├─ src/
│  └─ main/
│     ├─ java/            # Java source code (controllers/services/entities)
│     └─ resources/       # FXML, CSS, images, report templates
├─ mvnw                   # Maven wrapper (Linux/Mac)
├─ mvnw.cmd               # Maven wrapper (Windows)
└─ pom.xml                # Maven dependencies & plugins
>
> ---

## ✅ Prerequisites

- **JDK 17**
- **MySQL Server**
- (Optional) **IntelliJ IDEA** / **Scene Builder**

---

## ⚙️ Setup & Run

### 1) Clone the repository

```bash
git clone https://github.com/PraveenRusiru/Therapy_center.git
cd Therapy_center

2) Configure the database

Create a MySQL database (example):

CREATE DATABASE therapy_center;

Then update your DB configuration (commonly one of these locations):
	•	hibernate.cfg.xml, or
	•	a .properties file inside src/main/resources/

Make sure these values are correct:
	•	DB URL (example: jdbc:mysql://localhost:3306/therapy_center)
	•	Username / Password
	•	Hibernate Dialect (MySQL)

If your repo contains SQL scripts, run them to create tables (check src/main/resources/).
