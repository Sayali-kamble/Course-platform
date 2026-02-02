#  Course Platform API

---

## Project Overview

The Course Platform API is built using Spring Boot with JWT-based authentication.It allows users to browse, search, and view courses publicly without logging in.
Each course is organized into topics and subtopics. Users can register, log in, and receive a JWT token to access protected features.

After authentication, users can:
- Enroll in courses 
- Mark subtopics as completed only if they are enrolled in the corresponding course
- Track their learning progress

---

## Features

- User authentication and authorization with JWT
- View all courses
- View a course by ID
- Search courses and content
- Course Enrollment
- Progress Tracking
- View Progress

---

## Tech Stack
- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Swagger

---

## System Architecture
- Monolithic 
- REST API-based
- Layered architecture

---

## API Endpoints

- POST /api/auth/register: Register a new user
- POST /api/auth/login: Login an existing user
- GET /api/courses: Retrieves a list of all available courses.
- GET /api/courses/{courseId}: Fetches detailed information of a specific course
- GET /api/search?q=velocity: Searches courses, topics, and content based on the given keyword.
- POST /api/courses/{courseId}/enroll: Enrolls the authenticated user in the selected course.
- POST /api/subtopics/{subtopicId}/complete: Marks a subtopic as completed to track learning progress.
- GET /api/enrollments/{enrollmentId}/progress: Retrieves the user’s progress for an enrolled course.

---
## How to run this project

### Step 1: Clone the Repository

```text
git clone https://github.com/your-username/Course-platform.git
cd Course-platform
```

### Step 2: Configure Database

Update database details in application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/db_name
spring.datasource.username=root
spring.datasource.password=your_password
```

### step 3: Build the Project

```text
mvn clean install
```

### Step 4: Run the Application

```text
mvn spring-boot:run
```

OR run directly from IntelliJ:
- Open the main class annotated with @SpringBootApplication
- Click  Run

### Step 5: Access the Application

```text
http://localhost:8080
```

---
## API Documentation

### Swagger UI

- URL: ```  http://localhost:8080/swagger-ui.html ```

---

## Author

Sayali Kamble<br>
Java Developer | Backend Developer

---