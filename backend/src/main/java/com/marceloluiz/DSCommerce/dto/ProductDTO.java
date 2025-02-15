package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.Category;
import com.marceloluiz.DSCommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ProductDTO {
    private Long id;
    @Size(min = 3, max = 80, message = "Name must be between 3 and 80 characters")
    @NotBlank(message = "Field is required")
    private String name;

    @Size(min = 10, message = "Description must have at least 10 characters")
    @NotBlank(message = "Field is required")
    private String description;

    @jakarta.validation.constraints.NotNull(message = "Field is required")
    @Positive(message = "The price must be positive")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Must have at least one category")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(@NotNull Product product){
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();

        for(Category category : product.getCategories()){
            categories.add(new CategoryDTO(category));
        }
    }
}
