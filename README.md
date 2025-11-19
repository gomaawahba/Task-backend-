Multi-Tenant Management System – Microservices Architecture

A fully scalable Multi-Tenant SaaS platform built using Spring Boot Microservices, supporting multiple tenants with complete isolation for Tasks, Vehicles, Deals, and Users.

This project is designed for systems where each client (tenant) has separate data, separate database, and dynamic tenant resolution.

 Key Features
 Multi-Tenancy

Database-per-Tenant strategy

Tenants stored in a Master Database

Each request contains a X-Tenant-ID header

Middleware intercepts requests → selects correct DataSource dynamically

 Microservices

Tenant Service – register tenants, activate/deactivate tenants

Task Service – CRUD operations for tenant tasks

Vehicle Service – manage vehicles per tenant

Deals Service – manage offers/deals per tenant

Auth Service – multi-tenant JWT authentication

Gateway Service – routes all client traffic

Discovery Service (Eureka)
                  ┌────────────────────┐
                  │     Client App      │
                  └──────────┬──────────┘
                             │
                     X-Tenant-ID header
                             │
                  ┌──────────▼──────────┐
                  │     API Gateway      │
                  └──────────┬──────────┘
                             │
                     Service Discovery
                             │
     ┌────────────┬──────────┼─────────────┬────────────┐
     ▼            ▼          ▼             ▼            ▼
Tenant Service  Task Service  Vehicle Service  Deals Service  Auth Service
     │            │          │             │            │
     └───────Uses Dynamic Multi-Tenant DataSource──────┘









