package com.chernikov.taco_cloud.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// Хранилище учетных записей пользователей
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException;
}
