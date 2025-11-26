package com.example.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LocationDto {
    private Long id;
    
    @NotBlank(message = "Название города обязательно")
    @Size(min = 2, max = 50, message = "Название города должно содержать от 2 до 50 символов")
    private String city;
    
    @NotBlank(message = "Адрес обязателен")
    @Size(min = 5, max = 200, message = "Адрес должен содержать от 5 до 200 символов")
    private String address;
}
