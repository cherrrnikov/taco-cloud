package com.chernikov.taco_cloud.security;

import com.chernikov.taco_cloud.data.User;
import com.chernikov.taco_cloud.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        List<UserDetails> usersList = new ArrayList<>();
//        usersList.add(
//                new User(
//                        "buzz", passwordEncoder.encode("password"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//                )
//        );
//        usersList.add(
//                new User(
//                        "woody", passwordEncoder.encode("password"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//                )
//        );
//        return (UserDetailsService) new InMemoryUserDetailsManager(usersList);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username +"' not found");
        };
    }
}
