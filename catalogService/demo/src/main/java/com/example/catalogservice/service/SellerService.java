package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.entity.Product;
import com.example.catalogservice.entity.Variant;
import com.example.catalogservice.exception.ResourceNotFoundException;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.repository.ProductRepository;
import com.example.catalogservice.repository.VariantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    public Catalog createCatalog(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    public Catalog updateCatalog(Long id, Catalog catalogDetails) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + id));
        catalog.setName(catalogDetails.getName());
        catalog.setDescription(catalogDetails.getDescription());
        return catalogRepository.save(catalog);
    }

    public void deleteCatalog(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + id));
        catalogRepository.delete(catalog);
    }

    public Product createProduct(Long catalogId, Product product) {
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + catalogId));
        catalog.getProducts().add(product);
        catalogRepository.save(catalog);
        return product;
    }

    public Product updateProduct(Long catalogId, Long productId, Product productDetails) {
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + catalogId));
        Product product = catalog.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setQuantity(productDetails.getQuantity());
        catalogRepository.save(catalog);
        return product;
    }

    public void deleteProduct(Long catalogId, Long productId) {
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found for this id :: " + catalogId));
        Product product = catalog.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        catalog.getProducts().remove(product);
        catalogRepository.save(catalog);
    }

    public Variant createVariant(Long productId, Variant variant) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
            Variant existingVariant = product.getVariants().stream()
                    .filter(v -> v.getColor().equals(variant.getColor()) && v.getSize().equals(variant.getSize()))
                    .findFirst()
                    .orElse(null);
            if (existingVariant != null) {
                existingVariant.setQuantity(existingVariant.getQuantity() + 1);
            } else {
                variant.setQuantity(1);
                product.getVariants().add(variant);
            }
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
            return variant;
        }
        catch (ResourceNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public Variant updateVariant(Long productId, Long variantId, Variant variantDetails) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        Variant variant = product.getVariants().stream()
                .filter(v -> v.getId().equals(variantId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Variant not found for this id :: " + variantId));
        product.setQuantity(product.getQuantity() - variant.getQuantity() + variantDetails.getQuantity());
        variant.setPrice(variantDetails.getPrice());
        variant.setColor(variantDetails.getColor());
        variant.setSize(variantDetails.getSize());
        variant.setQuantity(variantDetails.getQuantity());
        productRepository.save(product);
        return variant;
    }

    public void deleteVariant(Long productId, Long variantId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        Variant variant = product.getVariants().stream()
                .filter(v -> v.getId().equals(variantId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Variant not found for this id :: " + variantId));
        product.setQuantity(product.getQuantity() - variant.getQuantity());
        product.getVariants().remove(variant);
        productRepository.save(product);
    }
}