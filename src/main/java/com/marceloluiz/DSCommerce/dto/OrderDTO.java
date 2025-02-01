package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.Order;
import com.marceloluiz.DSCommerce.entities.OrderItem;
import com.marceloluiz.DSCommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus status;

    private UserMinDTO client;
    private PaymentDTO payment;
    @NotEmpty(message = "Must have at least one item")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        status = order.getStatus();
        client = new UserMinDTO(order.getClient());
        payment = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());

        for(OrderItem item : order.getItems()){
            items.add(new OrderItemDTO(item));
        }
    }

    public Double getTotal(){
        double sum = 0.0;
        for(OrderItemDTO item : items){
            sum += item.getSubTotal();
        }
        return sum;
    }
}
