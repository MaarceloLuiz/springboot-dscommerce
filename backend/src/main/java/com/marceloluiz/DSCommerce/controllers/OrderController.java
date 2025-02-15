package com.marceloluiz.DSCommerce.controllers;

import com.marceloluiz.DSCommerce.dto.OrderDTO;
import com.marceloluiz.DSCommerce.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){ //PathVariable matches the value inserted on GetMapping
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto){ //@Valid will execute the validation we added to the DTO
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
