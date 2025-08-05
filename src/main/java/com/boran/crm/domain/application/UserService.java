package com.boran.crm.domain.application;

import com.boran.crm.domain.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByEmail(String email);

}
