package com.example.carrental.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;
    
    @NotNull(message = "ID аренды обязателен")
    private Long rentalId;
    
    @Positive(message = "Сумма платежа должна быть положительной")
    @DecimalMin(value = "0.01", message = "Сумма платежа должна быть больше 0")
    private double amount;
    
    @NotNull(message = "Дата платежа обязательна")
    private LocalDateTime paymentDate;
    
    @NotBlank(message = "Метод платежа обязателен")
    @Pattern(regexp = "^(CASH|CARD|BANK_TRANSFER)$", message = "Метод платежа должен быть: CASH, CARD или BANK_TRANSFER")
    private String paymentMethod;
}
