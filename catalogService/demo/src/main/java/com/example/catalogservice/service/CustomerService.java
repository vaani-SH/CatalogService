package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.entity.Product;
import com.example.catalogservice.entity.Variant;
import com.example.catalogservice.exception.ResourceNotFoundException;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.repository.ProductRepository;
import com.example.catalogservice.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    public List<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }

    public Optional<Catalog> getCatalogById(Long id) {
        return catalogRepository.findById(id);
    }

    public List<Product> getAllProducts(Long catalogId) {
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + catalogId));
        return catalog.getProducts();
    }

    public Optional<Product> getProductById(Long catalogId, Long productId) {
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + catalogId));
        return catalog.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    public List<Variant> getAllVariants(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return product.getVariants();
    }

    public Optional<Variant> getVariantById(Long productId, Long variantId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return product.getVariants().stream()
                .filter(variant -> variant.getId().equals(variantId))
                .findFirst();
    }
}
