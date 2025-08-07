package com.boran.crm.domain.service;

import com.boran.crm.domain.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    boolean existsByEmail(String email);

}
