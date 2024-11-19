package com.marceloluiz.DSCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class ProductDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final String imgUrl;
}
