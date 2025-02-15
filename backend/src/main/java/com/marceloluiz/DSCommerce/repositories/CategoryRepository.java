package com.marceloluiz.DSCommerce.repositories;

import com.marceloluiz.DSCommerce.entities.Category;
import com.marceloluiz.DSCommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
