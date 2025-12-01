package com.example.carrental.service;

import com.example.carrental.dto.PaymentDto;
import com.example.carrental.entity.Payment;
import com.example.carrental.mapper.PaymentMapper;
import com.example.carrental.repository.PaymentRepository;
import com.example.carrental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final RentalRepository rentalRepository;
    
    public Page<PaymentDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }
    
    public List<PaymentDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    
    public Optional<PaymentDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
    
    public PaymentDto save(PaymentDto dto) {
        Payment entity = mapper.toEntity(dto);
        if (dto.getRentalId() != null) {
            entity.setRental(rentalRepository.findById(dto.getRentalId()).orElse(null));
        }
        Payment saved = repository.save(entity);
        return mapper.toDto(saved);
    }
    
    public Optional<PaymentDto> update(Long id, PaymentDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setAmount(dto.getAmount());
                    existing.setPaymentDate(dto.getPaymentDate());
                    existing.setPaymentMethod(dto.getPaymentMethod());
                    if (dto.getRentalId() != null) {
                        existing.setRental(rentalRepository.findById(dto.getRentalId()).orElse(null));
                    }
                    return mapper.toDto(repository.save(existing));
                });
    }
    
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}