package com.example.carrental.dto;

import com.example.carrental.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    
    @NotBlank(message = "Имя клиента обязательно")
    @Size(min = 2, max = 100, message = "Имя должно содержать от 2 до 100 символов")
    private String name;
    
    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private String email;
    
    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Некорректный формат телефона")
    private String phone;
    
    @NotNull(message = "Роль пользователя обязательна")
    private Role role;
}
