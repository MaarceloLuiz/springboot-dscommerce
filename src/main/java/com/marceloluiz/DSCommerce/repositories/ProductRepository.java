package com.marceloluiz.DSCommerce.repositories;

import com.marceloluiz.DSCommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
