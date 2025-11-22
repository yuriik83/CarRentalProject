package com.example.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarModelDto {
    private Long id;
    
    @NotBlank(message = "Марка автомобиля обязательна")
    @Size(min = 2, max = 50, message = "Марка должна содержать от 2 до 50 символов")
    private String brand;
    
    @NotBlank(message = "Модель автомобиля обязательна")
    @Size(min = 2, max = 50, message = "Модель должна содержать от 2 до 50 символов")
    private String model;
    
    @NotBlank(message = "Тип топлива обязателен")
    private String fuelType;
    
    @Positive(message = "Дневная ставка должна быть положительной")
    @DecimalMin(value = "0.01", message = "Дневная ставка должна быть больше 0")
    private double dailyRate;
}
