# ✈️ Flight Reservations – Microservices Architecture

This project was developed as part of the final exam for the “Web-Oriented Applications and Services” course during the M2 MIAGE SITN program at Université Paris Dauphine-PSL.

## 🧾 Project Overview

The goal of this project is to design and implement a microservices-based architecture for an airline flight reservation system, modernizing the company's legacy monolithic application.

We implemented several core services using **Spring Boot**, **Spring Cloud**, **JPA**, and **REST APIs**, enabling:

- Flight search and booking
- Seat management and inventory control
- Passenger check-in
- Ticket generation and boarding pass issuance

Each domain (booking, check-in, inventory, etc.) is handled by a dedicated microservice communicating with others.

## 🧱 Architecture

The system consists of the following microservices:

- **Booking Service** – Seat reservation and payment trigger
- **Inventory Service** – Flight data and seat availability
- **Check-in Service** – Passenger check-in and seat confirmation
- **Boarding Service** – Boarding validation and flight log update
- **Seat Service** – Real-time seat locking and conflict handling
- **Notification / Event Bus** – RabbitMQ-based message handling
- **and more...**
  

The solution includes a **discovery server**, **API gateway**, and **configuration server**.

## ⚙️ Tech Stack

- Java 17
- Spring Boot / Spring Cloud
- Eureka, Feign, Config Server
- H2 / PostgreSQL
- Docker (for testing) # TODO

## 🚀 Main Features

- RESTful APIs for all services
- WireMock and JUnit tests for validation
- Swagger / AsyncAPI documentation # TODO

## 📍 Authors

- Julie Huyen – MIAGE SITN M2 – Université Paris Dauphine
- Joëlle Huyen – MIAGE SITN M2 – Université Paris Dauphine

---

