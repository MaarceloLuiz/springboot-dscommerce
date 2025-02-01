package com.marceloluiz.DSCommerce.controllers;

import com.marceloluiz.DSCommerce.dto.CategoryDTO;
import com.marceloluiz.DSCommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categoryDtoList = service.findAll();
        return ResponseEntity.ok(categoryDtoList);
    }
}
