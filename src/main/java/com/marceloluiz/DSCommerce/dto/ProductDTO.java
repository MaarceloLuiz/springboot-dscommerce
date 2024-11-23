package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.Product;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class ProductDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final String imgUrl;

    public ProductDTO(@NotNull Product product){
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }
}
