package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.entities.Product;
import com.marceloluiz.DSCommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public String teste(){
        Optional<Product> result = repository.findById(1L);
        return result.get().getName();
    }
}
