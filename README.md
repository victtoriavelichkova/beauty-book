# BeautyBook - Hair Salon Management System

## Overview

BeautyBook is a web application for managing a hair salon. The system allows administrators to manage clients, hairdressers, services and appointments, while registered users can browse services, view price lists and book appointments.

The project follows the MVC architecture and uses MySQL as a relational database.

---

## Tech Stack

* Java 17
* Spring Boot 3.4
* Spring MVC
* Spring Security
* Spring Data JPA
* Hibernate
* Thymeleaf
* MySQL
* Maven

---

## Features

### Authentication & Authorization

* User registration
* User login/logout
* BCrypt password hashing
* Role-based access control (ADMIN / USER)

### Client Management

* Create client
* View clients
* Edit client
* Delete client

### Hairdresser Management

* Create hairdresser
* View hairdressers
* Edit hairdresser
* Delete hairdresser

### Services Management

* Create services
* Edit services
* Delete services
* View services list
* View price list

### Appointment Management

* Book appointment
* View appointments
* Cancel appointments
* Appointment status tracking

---

## Domain Entities

* User
* Role
* Client
* Hairdresser
* SalonService
* Appointment

### Relationships

* One Role → Many Users
* One Client → Many Appointments
* One Hairdresser → Many Appointments
* One Service → Many Appointments

---

## Validation

The application includes server-side validation for:

* Registration form
* Client form
* Hairdresser form
* Service form
* Appointment form

Validation messages are displayed next to the invalid fields.

---

## Security

Implemented with Spring Security:

* Authentication
* Authorization
* Protected routes
* BCrypt password encryption
* Role-based access control

---

## Database

Database name:

beauty_book_db

Tables:

* users
* roles
* clients
* hairdressers
* services
* appointments

---

## How to Run

1. Create MySQL database:

```sql
CREATE DATABASE beauty_book_db;
```

2. Configure database credentials in:

```properties
application.properties
```

3. Start the application.

4. Open:

```text
http://localhost:8080
```

---

## Default Admin Account

Username:

```text
admin
```

Password:

```text
admin
```

---

## Author

Victoria Velichkova
