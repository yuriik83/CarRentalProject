package com.example.carrental.mapper;

import org.springframework.stereotype.Component;
import com.example.carrental.entity.Payment;
import com.example.carrental.dto.PaymentDto;

@Component
public class PaymentMapper {

    public PaymentDto toDto(Payment entity) {
        if (entity == null) return null;
        
        PaymentDto dto = new PaymentDto();
        dto.setId(entity.getId());
        dto.setRentalId(entity.getRental() != null ? entity.getRental().getId() : null);
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentMethod(entity.getPaymentMethod());
        return dto;
    }

    public Payment toEntity(PaymentDto dto) {
        if (dto == null) return null;
        
        Payment entity = new Payment();
        entity.setAmount(dto.getAmount());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setPaymentMethod(dto.getPaymentMethod());
        return entity;
    }
}
