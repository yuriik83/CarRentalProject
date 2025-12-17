package com.example.carrental.service;

import com.example.carrental.entity.RefreshToken;
import com.example.carrental.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    
    private final RefreshTokenRepository repository;
    
    @Value("${jwt.refresh.expiration:604800}")
    private int refreshTokenExpiration;
    
    public RefreshToken createRefreshToken(String username) {
        // Удаляем старый токен пользователя
        repository.deleteByUsername(username);
        
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenExpiration));
        
        return repository.save(refreshToken);
    }
    
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token);
    }
    
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            repository.delete(token);
            throw new RuntimeException("Refresh token expired. Please make a new signin request");
        }
        return token;
    }
    
    @Transactional
    public void deleteByUsername(String username) {
        repository.deleteByUsername(username);
    }
    
    @Transactional
    public void deleteExpiredTokens() {
        repository.deleteExpiredTokens(LocalDateTime.now());
    }
}