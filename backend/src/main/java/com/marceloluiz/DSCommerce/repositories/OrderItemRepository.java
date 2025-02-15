package com.marceloluiz.DSCommerce.repositories;

import com.marceloluiz.DSCommerce.entities.Order;
import com.marceloluiz.DSCommerce.entities.OrderItem;
import com.marceloluiz.DSCommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
