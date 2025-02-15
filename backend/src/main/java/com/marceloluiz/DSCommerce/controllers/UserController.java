package com.marceloluiz.DSCommerce.controllers;

import com.marceloluiz.DSCommerce.dto.ProductDTO;
import com.marceloluiz.DSCommerce.dto.UserDTO;
import com.marceloluiz.DSCommerce.services.ProductService;
import com.marceloluiz.DSCommerce.services.UserService;
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
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> findMe(){
        UserDTO dto = service.findMe();
        return ResponseEntity.ok(dto);
    }
}
