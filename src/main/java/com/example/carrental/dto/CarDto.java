package com.example.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarDto {
    private Long id;
    
    @NotNull(message = "ID модели автомобиля обязателен")
    private Long carModelId;
    
    @NotNull(message = "ID локации обязателен")
    private Long locationId;
    
    @NotBlank(message = "Номерной знак обязателен")
    @Pattern(regexp = "^[A-Z0-9]{6,10}$", message = "Номерной знак должен содержать 6-10 символов (буквы и цифры)")
    private String licensePlate;
    
    @NotBlank(message = "Статус автомобиля обязателен")
    @Pattern(regexp = "^(AVAILABLE|RENTED|MAINTENANCE)$", message = "Статус должен быть: AVAILABLE, RENTED или MAINTENANCE")
    private String status;
}
