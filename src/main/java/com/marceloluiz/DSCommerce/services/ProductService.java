package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.dto.CategoryDTO;
import com.marceloluiz.DSCommerce.dto.ProductDTO;
import com.marceloluiz.DSCommerce.dto.ProductMinDTO;
import com.marceloluiz.DSCommerce.entities.Category;
import com.marceloluiz.DSCommerce.entities.Product;
import com.marceloluiz.DSCommerce.repositories.ProductRepository;
import com.marceloluiz.DSCommerce.services.exceptions.DatabaseException;
import com.marceloluiz.DSCommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable){
        return repository.searchByName(name, pageable).map(ProductMinDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoFromEntity(entity, dto);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoFromEntity(entity, dto);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)) throw new ResourceNotFoundException("Resource not found.");

        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Referential integrity failure");
        }
    }

    private void copyDtoFromEntity(Product entity, ProductDTO dto) {
        entity.setName(dto.getName() != null ? dto.getName() : entity.getName());
        entity.setDescription(dto.getDescription() != null ? dto.getDescription() : entity.getDescription());
        entity.setPrice(dto.getPrice() != null ? dto.getPrice() : entity.getPrice());
        entity.setImgUrl(dto.getImgUrl() != null ? dto.getImgUrl() : entity.getImgUrl());

        entity.getCategories().clear();
        for(CategoryDTO categoryDto : dto.getCategories()){
            Category category = new Category();
            category.setId(categoryDto.getId());

            entity.getCategories().add(category);
        }
    }
}
