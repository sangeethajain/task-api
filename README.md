# Task Management API

A simple RESTful API built using Spring Boot to manage tasks.
This project demonstrates backend development concepts including CRUD operations, validation, exception handling, and integration testing.

## Features
Create, Read, Update, Delete tasks
Input validation using Jakarta Validation
Global exception handling
In-memory H2 database
RESTful API design
Integration testing using MockMvc

## Tech Stack
Java 17
Spring Boot
Spring Data JPA
H2 Database
JUnit 5 & MockMvc
Maven

## Project Structure
controller → Handles HTTP requests  
service → Business logic  
repository → Database interaction  
model → Entity classes  
exception → Global error handling  

## API-Endpoints
| Method | Endpoint    | Description     |
| ------ | ----------- | --------------- |
| GET    | /tasks      | Get all tasks   |
| GET    | /tasks/{id} | Get task by ID  |
| POST   | /tasks      | Create new task |
| PUT    | /tasks/{id} | Update task     |
| DELETE | /tasks/{id} | Delete task     |

## How to Run
mvn clean install

mvn spring-boot:run

## Running Tests
mvn test

## Sample Request
POST /tasks
</>JSON
{
  "title": "Learn Spring Boot",
  "description": "Build REST APIs",
  "completed": false
}

## Database 

In-memory H2 Database

## Future Improvements

Filtering

Swagger API documentation

