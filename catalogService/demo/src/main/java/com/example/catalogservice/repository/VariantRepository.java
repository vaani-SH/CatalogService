package com.example.catalogservice.repository;

import com.example.catalogservice.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {}
