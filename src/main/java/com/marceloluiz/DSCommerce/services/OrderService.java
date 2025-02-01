package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.dto.OrderDTO;
import com.marceloluiz.DSCommerce.entities.Order;
import com.marceloluiz.DSCommerce.repositories.OrderRepository;
import com.marceloluiz.DSCommerce.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found"));
        return new OrderDTO(order);
    }

}
