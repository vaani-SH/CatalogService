# Catalog Service

## Overview
The Catalog Service is a Spring Boot application that manages catalogs, products, and variants. It provides RESTful APIs to create, update, delete, and retrieve catalogs, products, and variants.

## Features
- Create, update, delete, and retrieve catalogs
- Create, update, delete, and retrieve products within catalogs
- Create, update, delete, and retrieve variants within products
- Exception handling for resource not found scenarios
- Logging of exceptions

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- H2 Database (for development and testing)

## API Endpoints
Catalogs
- Create Catalog: POST /api/seller/catalogs
- Update Catalog: PUT /api/seller/catalogs/{id}
- Delete Catalog: DELETE /api/seller/catalogs/{id}
- Get Catalogs: GET /api/customer/catalogs
Products
- Create Product: POST /api/seller/catalogs/{catalogId}/products
- Update Product: PUT /api/seller/catalogs/{catalogId}/products/{productId}
- Delete Product: DELETE /api/seller/catalogs/{catalogId}/products/{productId}
- Get Product: GET /api/catalogs/{catalogId}/products/{productId}
Variants
Create Variant: POST /api/products/{productId}/variants
Update Variant: PUT /api/products/{productId}/variants/{variantId}
Delete Variant: DELETE /api/products/{productId}/variants/{variantId}
Get Variant: GET /api/products/{productId}/variants/{variantId}
Exception Handling
The application handles ResourceNotFoundException and returns a structured error response with a message and timestamp.
