package com.marceloluiz.DSCommerce.controllers;

import com.marceloluiz.DSCommerce.dto.ProductDTO;
import com.marceloluiz.DSCommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){ //PathVariable matches the value inserted on GetMapping
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
