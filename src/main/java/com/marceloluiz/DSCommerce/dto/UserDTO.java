package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.User;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class UserDTO {
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;

    private List<String> roles = new ArrayList<>();

    public UserDTO(User entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();

        for(GrantedAuthority role : entity.getRoles()){
            roles.add(role.getAuthority());
        }
    }
}
