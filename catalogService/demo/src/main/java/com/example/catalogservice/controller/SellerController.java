package com.example.catalogservice.controller;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.entity.Product;
import com.example.catalogservice.entity.Variant;
import com.example.catalogservice.exception.ResourceNotFoundException;
import com.example.catalogservice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller/catalogs")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public Catalog createCatalog(@RequestBody Catalog catalog) {
        return sellerService.createCatalog(catalog);
    }

    @PutMapping("/{id}")
    public Catalog updateCatalog(@PathVariable Long id, @RequestBody Catalog catalogDetails) {
        return sellerService.updateCatalog(id, catalogDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCatalog(@PathVariable Long id) {
        sellerService.deleteCatalog(id);
    }

    @PostMapping("/{catalogId}/products")
    public Product createProduct(@PathVariable Long catalogId, @RequestBody Product product) {
        return sellerService.createProduct(catalogId, product);
    }

    @PutMapping("/{catalogId}/products/{productId}")
    public Product updateProduct(@PathVariable Long catalogId, @PathVariable Long productId, @RequestBody Product productDetails) {
        return sellerService.updateProduct(catalogId, productId, productDetails);
    }

    @DeleteMapping("/{catalogId}/products/{productId}")
    public void deleteProduct(@PathVariable Long catalogId, @PathVariable Long productId) {
        sellerService.deleteProduct(catalogId, productId);
    }

    @PostMapping("/products/{productId}/variants")
    public Variant createVariant(@PathVariable Long productId, @RequestBody Variant variant) {
        return sellerService.createVariant(productId, variant);
    }

    @PutMapping("/products/{productId}/variants/{variantId}")
    public Variant updateVariant(@PathVariable Long productId, @PathVariable Long variantId, @RequestBody Variant variantDetails) {
        return sellerService.updateVariant(productId, variantId, variantDetails);
    }

    @DeleteMapping("/products/{productId}/variants/{variantId}")
    public void deleteVariant(@PathVariable Long productId, @PathVariable Long variantId) {
        sellerService.deleteVariant(productId, variantId);
    }
}