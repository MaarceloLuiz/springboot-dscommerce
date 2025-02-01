package com.marceloluiz.DSCommerce.services;

import com.marceloluiz.DSCommerce.entities.Role;
import com.marceloluiz.DSCommerce.entities.User;
import com.marceloluiz.DSCommerce.projections.UserDetailsProjection;
import com.marceloluiz.DSCommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> users = userRepository.searchUserAndRolesByEmail(username);
        if (users.isEmpty()) throw new UsernameNotFoundException("User not found");

        User user = new User();
        user.setEmail(username);
        user.setPassword(users.getFirst().getPassword());
        users.forEach(projection ->
                user.addRole(new Role(projection.getRoleId(), projection.getAuthority()))
        );
        return user;
    }
}
