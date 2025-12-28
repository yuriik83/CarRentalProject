package com.example.carrental.config;

import com.example.carrental.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig {
    
    private final RefreshTokenService refreshTokenService;
    
    @Scheduled(fixedRate = 3600000) // Каждый час
    public void cleanupExpiredTokens() {
        refreshTokenService.deleteExpiredTokens();
    }
}