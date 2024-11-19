package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.dto.ProductDTO;
import com.marceloluiz.DSCommerce.entities.Product;
import com.marceloluiz.DSCommerce.repositories.ProductRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).get();
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imgUrl(product.getImgUrl())
                .build();
    }
}
