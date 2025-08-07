package com.boran.crm.domain.service;


import com.boran.crm.domain.entity.RefreshToken;
import com.boran.crm.domain.entity.User;
import com.boran.crm.domain.repository.RefreshTokenRepository;
import com.boran.crm.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.refresh-token.expiration-ms}")
    private Long refreshTokenDurationMs;

    public RefreshToken createRefreshToken(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + userId));

        refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationMs / 1000))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }
    public boolean isTokenExpired(RefreshToken token){
        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }
    public void deleteByUserId(String userId) {
        userRepository.findById(userId).ifPresent(refreshTokenRepository::deleteByUser);
    }
}
