package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@Getter
public class ProductMinDTO {
    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDTO(@NotNull Product product){
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }
}
