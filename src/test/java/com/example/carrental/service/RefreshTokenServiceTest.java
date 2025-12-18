package com.example.carrental.service;

import com.example.carrental.entity.RefreshToken;
import com.example.carrental.repository.RefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RefreshTokenServiceTest {

    @Mock
    private RefreshTokenRepository repository;

    @InjectMocks
    private RefreshTokenService refreshTokenService;

    private RefreshToken refreshToken;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(refreshTokenService, "refreshTokenExpiration", 604800);
        
        refreshToken = new RefreshToken();
        refreshToken.setId(1L);
        refreshToken.setToken("test-refresh-token");
        refreshToken.setUsername("testuser");
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));
        refreshToken.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void createRefreshToken_ValidUsername_ReturnsRefreshToken() {
        // Given
        String username = "testuser";
        when(repository.save(any(RefreshToken.class))).thenReturn(refreshToken);

        // When
        RefreshToken result = refreshTokenService.createRefreshToken(username);

        // Then
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(repository).deleteByUsername(username);
        verify(repository).save(any(RefreshToken.class));
    }

    @Test
    void findByToken_ExistingToken_ReturnsRefreshToken() {
        // Given
        String token = "test-refresh-token";
        when(repository.findByToken(token)).thenReturn(Optional.of(refreshToken));

        // When
        Optional<RefreshToken> result = refreshTokenService.findByToken(token);

        // Then
        assertTrue(result.isPresent());
        assertEquals(token, result.get().getToken());
        verify(repository).findByToken(token);
    }

    @Test
    void findByToken_NonExistingToken_ReturnsEmpty() {
        // Given
        when(repository.findByToken(anyString())).thenReturn(Optional.empty());

        // When
        Optional<RefreshToken> result = refreshTokenService.findByToken("invalid-token");

        // Then
        assertFalse(result.isPresent());
        verify(repository).findByToken("invalid-token");
    }

    @Test
    void verifyExpiration_ValidToken_ReturnsToken() {
        // Given
        refreshToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        // When
        RefreshToken result = refreshTokenService.verifyExpiration(refreshToken);

        // Then
        assertNotNull(result);
        assertEquals(refreshToken.getToken(), result.getToken());
        verify(repository, never()).delete(any());
    }

    @Test
    void verifyExpiration_ExpiredToken_ThrowsException() {
        // Given
        refreshToken.setExpiryDate(LocalDateTime.now().minusHours(1));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> refreshTokenService.verifyExpiration(refreshToken));
        
        assertEquals("Refresh token expired. Please make a new signin request", exception.getMessage());
        verify(repository).delete(refreshToken);
    }

    @Test
    void deleteByUsername_ValidUsername_CallsRepository() {
        // Given
        String username = "testuser";

        // When
        refreshTokenService.deleteByUsername(username);

        // Then
        verify(repository).deleteByUsername(username);
    }

    @Test
    void deleteExpiredTokens_CallsRepository() {
        // When
        refreshTokenService.deleteExpiredTokens();

        // Then
        verify(repository).deleteExpiredTokens(any(LocalDateTime.class));
    }
}