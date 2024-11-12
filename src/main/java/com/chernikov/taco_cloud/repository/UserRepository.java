package com.chernikov.taco_cloud.repository;

import com.chernikov.taco_cloud.data.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
