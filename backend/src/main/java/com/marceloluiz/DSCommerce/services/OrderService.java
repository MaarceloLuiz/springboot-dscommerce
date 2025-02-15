package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.dto.OrderDTO;
import com.marceloluiz.DSCommerce.dto.OrderItemDTO;
import com.marceloluiz.DSCommerce.entities.Order;
import com.marceloluiz.DSCommerce.entities.OrderItem;
import com.marceloluiz.DSCommerce.entities.OrderStatus;
import com.marceloluiz.DSCommerce.entities.Product;
import com.marceloluiz.DSCommerce.repositories.OrderItemRepository;
import com.marceloluiz.DSCommerce.repositories.OrderRepository;
import com.marceloluiz.DSCommerce.repositories.ProductRepository;
import com.marceloluiz.DSCommerce.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found"));

        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());

        for(OrderItemDTO itemDto : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .price(product.getPrice())
                    .quantity(itemDto.getQuantity())
                    .build();

            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
