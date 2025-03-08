# Catalog Service

## Overview
The Catalog Service is a Spring Boot application that manages catalogs, products, and variants. It provides RESTful APIs to create, update, delete, and retrieve catalogs, products, and variants.

## Features
- Create, update, delete, and retrieve catalogs
- Create, update, delete, and retrieve products within catalogs
- Create, update, delete, and retrieve variants within products

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
- Update Product: PUT /api/seller/catalogs/products/{productId}
- Delete Product: DELETE /api/seller/catalogs/products/{productId}
- Get Product by ID: GET /api/customer/catalogs/products/{productId}
- Get Products: GET /api/customer/catalogs/products
Variants
- Create Variant: POST /api/seller/catalogs/products/{productId}/variants
- Update Variant: PUT /api/seller/catalogs/products/{productId}/variants/{variantId}
- Delete Variant: DELETE /api/seller/catalogs/products/{productId}/variants/{variantId}
- Get Variant: GET /api/customer/catalogs/products/{productId}/variants/{variantId}
