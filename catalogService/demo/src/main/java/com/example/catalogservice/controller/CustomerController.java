package com.example.catalogservice.controller;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.entity.Product;
import com.example.catalogservice.entity.Variant;
import com.example.catalogservice.exception.ResourceNotFoundException;
import com.example.catalogservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/catalogs")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Catalog> getAllCatalogs() {
        return customerService.getAllCatalogs();
    }

    @GetMapping("/{id}")
    public Catalog getCatalogById(@PathVariable Long id) {
        return customerService.getCatalogById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + id));
    }

    @GetMapping("/{catalogId}/products")
    public List<Product> getAllProducts(@PathVariable Long catalogId) {
        return customerService.getAllProducts(catalogId);
    }

    @GetMapping("/{catalogId}/products/{productId}")
    public Product getProductById(@PathVariable Long catalogId, @PathVariable Long productId) {
        return customerService.getProductById(catalogId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
    }

    @GetMapping("/products/{productId}/variants")
    public List<Variant> getAllVariants(@PathVariable Long productId) {
        return customerService.getAllVariants(productId);
    }

    @GetMapping("/products/{productId}/variants/{variantId}")
    public Variant getVariantById(@PathVariable Long productId, @PathVariable Long variantId) {
        return customerService.getVariantById(productId, variantId)
                .orElseThrow(() -> new ResourceNotFoundException("Variant not found for this id :: " + variantId));
    }
}