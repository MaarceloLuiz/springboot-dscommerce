package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.Category;
import com.marceloluiz.DSCommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@Getter
public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO(@NotNull Category category){
        id = category.getId();
        name = category.getName();
    }
}
