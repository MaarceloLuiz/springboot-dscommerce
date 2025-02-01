package com.marceloluiz.DSCommerce.repositories;

import com.marceloluiz.DSCommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
