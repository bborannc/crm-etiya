package com.boran.crm.domain.service.abstracts;

import com.boran.crm.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    User update(User user);
    void delete(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
    List<User> findAll();

}
