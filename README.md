Customer Service
----------------------

Customer Service is a Spring Boot microservice responsible for managing customer data in the system. It provides REST APIs for CRUD operations along with support for pagination, sorting, and filtering.

This service acts as a **data provider** and is consumed by other services such as Order Service.

Tech Stack :
-------------
Java: 21
Spring Boot: 3.x
Database: MySQL
Build Tool: Maven

Features :
----------
* RESTful API design
* Layered Architecture (Controller, Service, Repository)
* DTO Pattern
 --> CustomerRequestDTO
 --> CustomerResponseDTO
* Manual Mapping (Entity ↔ DTO)
* Input Validation using Jakarta Validation
* Global Exception Handling
* Pagination
* Sorting
* Filtering
* Swagger API Documentation

Project Structure
-------------------
customermanagement
│── controller
│── service
│── repository
│── dto
│── entity
│── exception
│── mapper

API Base URL :
-------------
http://localhost:3001/api/v1/customers

API Endpoints :
--------------
Method	Endpoint	Description
GET	/	Get all customers (pagination & sorting)
GET	/search	Filter customers
GET	/{id}	Get customer by ID
POST	/	Create new customer
PUT	/{id}	Update customer
DELETE	/{id}	Delete customer


Customer Entity Fields
-------------------------
{
  "id": 1,
  "customerName": "John Doe",
  "email": "john@example.com",
  "phone": 9876543210,
  "address": "Hyderabad, India"
}

Validation Rules :
-------------------
customerName → Cannot be blank
email → Must be valid & unique
phone →
Must be 10 digits
Must be unique
address → Optional

Swagger Documentation :
-------------------------
Access API docs at:

http://localhost:3001/swagger-ui/index.html

How to Run :
-------------
1. Clone the repository
git clone <your-repo-url>
2. Navigate to project
cd customermanagement
3. Configure MySQL in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
4. Run the application
mvn spring-boot:run
5. Server runs on
http://localhost:3001

Exception Handling
---------------------
Centralized using Global Exception Handler
Returns structured and consistent error responses

Architecture
-------------
Controller Layer → Handles HTTP requests
Service Layer → Business logic
Repository Layer → Database interaction
DTO Layer → Request/Response separation
Mapper Layer → Manual mapping between Entity & DTO

Constraints & Database Rules
------------------------------
Email → Unique & Not Null
Phone → Unique & Not Null

Future Enhancements :
------------------------
- Service discovery (Eureka)
- API Gateway integration
- Unit and integration testing
Author
--------
MUNI BALAJI N
