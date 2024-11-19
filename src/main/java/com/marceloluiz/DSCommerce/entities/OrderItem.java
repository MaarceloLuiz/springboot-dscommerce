package com.marceloluiz.DSCommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Required by Hibernate
@Data
@Entity
@Table(name = "tb_order_item")
public class OrderItem {
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;
    private Double price;

    @Builder
    public OrderItem(Order order, Product product, Double price, Integer quantity) {
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.price = price;
        this.quantity = quantity;
    }

    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
        this.id.setOrder(order);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        this.id.setProduct(product);
    }
}
