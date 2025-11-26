package com.example.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RentalDto {
    private Long id;
    
    @NotNull(message = "ID автомобиля обязателен")
    private Long carId;
    
    @NotNull(message = "ID клиента обязателен")
    private Long customerId;
    
    @NotNull(message = "Дата начала аренды обязательна")
    @Future(message = "Дата начала должна быть в будущем")
    private LocalDateTime startDate;
    
    @NotNull(message = "Дата окончания аренды обязательна")
    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDateTime endDate;
    
    @Positive(message = "Общая стоимость должна быть положительной")
    @DecimalMin(value = "0.01", message = "Общая стоимость должна быть больше 0")
    private double totalCost;
}
