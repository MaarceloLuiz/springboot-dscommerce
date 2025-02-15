package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.dto.CategoryDTO;
import com.marceloluiz.DSCommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        return repository.findAll().stream().map(CategoryDTO::new).toList();
    }
}
