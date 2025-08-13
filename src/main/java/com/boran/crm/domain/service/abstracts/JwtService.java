package com.boran.crm.domain.service.abstracts;

import com.boran.crm.domain.entity.User;

public interface JwtService {
    String generateToken(User user);
    String extractUsername(String token);
    boolean isTokenValid(String token, User user);
}
