package com.boran.crm.domain.service.concretes;

import com.boran.crm.domain.entity.User;
import com.boran.crm.domain.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService; // User veritabanı işlemleri için

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Email ile kullanıcıyı bul
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: " + email
                ));

        // UserDetails objesi oluştur
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name()) // USER veya ADMIN
                .build();
    }
}
