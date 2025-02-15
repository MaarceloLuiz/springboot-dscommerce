package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserMinDTO {
    private Long id;
    private String name;

    public UserMinDTO(User user) {
        id = user.getId();
        name = user.getName();
    }
}
