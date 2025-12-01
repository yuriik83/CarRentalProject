package com.example.carrental.service;

import com.example.carrental.dto.CustomerDto;
import com.example.carrental.entity.Customer;
import com.example.carrental.mapper.CustomerMapper;
import com.example.carrental.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    
    public Page<CustomerDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }
    
    public List<CustomerDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    
    public Optional<CustomerDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
    
    public CustomerDto save(CustomerDto dto) {
        Customer entity = mapper.toEntity(dto);
        Customer saved = repository.save(entity);
        return mapper.toDto(saved);
    }
    
    public Optional<CustomerDto> update(Long id, CustomerDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setEmail(dto.getEmail());
                    existing.setPhone(dto.getPhone());
                    existing.setRole(dto.getRole());
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