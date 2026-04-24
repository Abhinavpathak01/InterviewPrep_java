# 🚀 Smart Interview Preparation Backend

A backend system built using Spring Boot that allows users to practice interview questions with secure authentication and personalized features.

---

## 🔥 Features

- 🔐 JWT Authentication (Login & Signup)
- 🛡️ Secure APIs using Spring Security
- 📚 Add and View Questions
- 🔍 Search Questions by Title
- 🎯 Filter Questions by Difficulty
- ⭐ Add to Favorites
- ❌ Remove from Favorites
- 👤 User-specific data handling

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- MySQL
- Maven

---

## 📡 API Endpoints

### 🔓 Public APIs

- `POST /users/signup`
- `POST /users/login`

---

### 🔐 Protected APIs

- `GET /questions`
- `POST /questions`
- `GET /questions?difficulty=EASY`
- `GET /questions?title=sum`
- `POST /questions/{id}/favorite`
- `GET /questions/favorites`
- `DELETE /questions/{id}/favorite`

---

## 📌 Example API Usage

### 🔐 Login

**Request:**

```
POST /users/login
```

```json
{
  "email": "abhinav@gmail.com",
  "password": "1234"
}
```

**Response:**

```json
{
  "message": "Login successful",
  "status": true,
  "token": "JWT_TOKEN"
}
```

---

### 📚 Get Questions

**Request:**

```
GET /questions
```

**Response:**

```json
[
  {
    "id": 1,
    "title": "Two Sum",
    "description": "Find two numbers that add up to target",
    "difficulty": "EASY"
  }
]
```

---

### ⭐ Add to Favorites

**Request:**

```
POST /questions/1/favorite
```

**Response:**

```
Added to favorites
```

---

### ❌ Remove from Favorites

**Request:**

```
DELETE /questions/1/favorite
```

**Response:**

```
Removed from favorites
```


## ▶️ How to Run

1. Clone the repository

```bash
git clone https://github.com/Abhinavpathak01/InterviewPrep_java.git