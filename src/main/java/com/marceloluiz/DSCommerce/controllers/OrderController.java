package com.marceloluiz.DSCommerce.controllers;

import com.marceloluiz.DSCommerce.dto.OrderDTO;
import com.marceloluiz.DSCommerce.dto.ProductDTO;
import com.marceloluiz.DSCommerce.dto.ProductMinDTO;
import com.marceloluiz.DSCommerce.services.OrderService;
import com.marceloluiz.DSCommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){ //PathVariable matches the value inserted on GetMapping
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
}
