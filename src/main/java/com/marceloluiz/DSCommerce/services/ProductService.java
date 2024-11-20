package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.dto.ProductDTO;
import com.marceloluiz.DSCommerce.entities.Product;
import com.marceloluiz.DSCommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imgUrl(dto.getImgUrl())
                .build();

        entity = repository.save(entity);

        return new ProductDTO(entity);
    }
}
