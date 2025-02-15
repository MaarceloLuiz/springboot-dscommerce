package com.marceloluiz.DSCommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
@Table(name = "tb_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @Override
    public String getAuthority(){
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(authority, role.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(authority);
    }
}
