package com.boran.crm.domain.service.concretes;


import com.boran.crm.domain.repository.UserRepository;
import com.boran.crm.domain.entity.User;
import com.boran.crm.domain.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
