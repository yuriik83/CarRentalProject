package com.example.carrental.controller;

import com.example.carrental.dto.JwtResponse;
import com.example.carrental.dto.LoginRequest;
import com.example.carrental.dto.RefreshTokenRequest;
import com.example.carrental.entity.RefreshToken;
import com.example.carrental.security.JwtUtil;
import com.example.carrental.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication", description = "Аутентификация и авторизация")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    @Operation(summary = "Аутентификация пользователя")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(
            accessToken, 
            refreshToken.getToken(), 
            userDetails.getUsername(),
            jwtUtil.getExpirationTime()
        ));
    }
    
    @PostMapping("/refresh")
    @Operation(summary = "Обновление токена")
    public ResponseEntity<JwtResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        
        return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshToken::getUsername)
            .map(username -> {
                String accessToken = jwtUtil.generateToken(
                    org.springframework.security.core.userdetails.User.builder()
                        .username(username)
                        .password("")
                        .authorities("ROLE_USER")
                        .build()
                );
                RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(username);
                
                return ResponseEntity.ok(new JwtResponse(
                    accessToken,
                    newRefreshToken.getToken(),
                    username,
                    jwtUtil.getExpirationTime()
                ));
            })
            .orElseThrow(() -> new RuntimeException("Неверный рефреш токен"));
    }

    @GetMapping("/user")
    @Operation(summary = "Получить информацию о текущем пользователе")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("authorities", authentication.getAuthorities());
        userInfo.put("authenticated", authentication.isAuthenticated());
        
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/logout")
    @Operation(summary = "Выход из системы")
    public ResponseEntity<Map<String, String>> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            refreshTokenService.deleteByUsername(authentication.getName());
        }
        SecurityContextHolder.clearContext();
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Успешный выход из системы");
        
        return ResponseEntity.ok(response);
    }
}