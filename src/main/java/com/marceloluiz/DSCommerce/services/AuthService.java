package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.entities.User;
import com.marceloluiz.DSCommerce.services.exceptions.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    public void validateSelfOrAdmin(long userId){
        User authenticatedUser = userService.authenticated();
        if(!authenticatedUser.hasRole("ROLE_ADMIN") && !authenticatedUser.getId().equals(userId)){
            throw new ForbiddenException("Access Denied!");
        }
    }
}
