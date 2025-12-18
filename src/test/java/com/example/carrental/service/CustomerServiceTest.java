package com.example.carrental.service;

import com.example.carrental.dto.CustomerDto;
import com.example.carrental.entity.Customer;
import com.example.carrental.entity.Role;
import com.example.carrental.mapper.CustomerMapper;
import com.example.carrental.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("+1234567890");
        customer.setRole(Role.USER);

        customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("John Doe");
        customerDto.setEmail("john@example.com");
        customerDto.setPhone("+1234567890");
        customerDto.setRole(Role.USER);
    }

    @Test
    void findById_ExistingCustomer_ReturnsCustomerDto() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(mapper.toDto(customer)).thenReturn(customerDto);

        // When
        Optional<CustomerDto> result = customerService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(customerDto.getName(), result.get().getName());
        assertEquals(customerDto.getEmail(), result.get().getEmail());
        verify(repository).findById(1L);
        verify(mapper).toDto(customer);
    }

    @Test
    void findById_NonExistingCustomer_ReturnsEmpty() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<CustomerDto> result = customerService.findById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(repository).findById(999L);
        verify(mapper, never()).toDto(any());
    }

    @Test
    void save_ValidCustomer_ReturnsCustomerDto() {
        // Given
        when(mapper.toEntity(customerDto)).thenReturn(customer);
        when(repository.save(customer)).thenReturn(customer);
        when(mapper.toDto(customer)).thenReturn(customerDto);

        // When
        CustomerDto result = customerService.save(customerDto);

        // Then
        assertNotNull(result);
        assertEquals(customerDto.getName(), result.getName());
        verify(mapper).toEntity(customerDto);
        verify(repository).save(customer);
        verify(mapper).toDto(customer);
    }

    @Test
    void findAll_WithPagination_ReturnsPageOfCustomers() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Customer> customers = Arrays.asList(customer);
        Page<Customer> customerPage = new PageImpl<>(customers, pageable, 1);
        
        when(repository.findAll(pageable)).thenReturn(customerPage);
        when(mapper.toDto(customer)).thenReturn(customerDto);

        // When
        Page<CustomerDto> result = customerService.findAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(customerDto.getName(), result.getContent().get(0).getName());
        verify(repository).findAll(pageable);
    }

    @Test
    void update_ExistingCustomer_ReturnsUpdatedCustomer() {
        // Given
        CustomerDto updateDto = new CustomerDto();
        updateDto.setName("Jane Doe");
        updateDto.setEmail("jane@example.com");
        updateDto.setPhone("+0987654321");
        updateDto.setRole(Role.ADMIN);

        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);
        when(mapper.toDto(any(Customer.class))).thenReturn(updateDto);

        // When
        Optional<CustomerDto> result = customerService.update(1L, updateDto);

        // Then
        assertTrue(result.isPresent());
        assertEquals(updateDto.getName(), result.get().getName());
        verify(repository).findById(1L);
        verify(repository).save(any(Customer.class));
    }

    @Test
    void update_NonExistingCustomer_ReturnsEmpty() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<CustomerDto> result = customerService.update(999L, customerDto);

        // Then
        assertFalse(result.isPresent());
        verify(repository).findById(999L);
        verify(repository, never()).save(any());
    }

    @Test
    void deleteById_ExistingCustomer_ReturnsTrue() {
        // Given
        when(repository.existsById(1L)).thenReturn(true);

        // When
        boolean result = customerService.deleteById(1L);

        // Then
        assertTrue(result);
        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteById_NonExistingCustomer_ReturnsFalse() {
        // Given
        when(repository.existsById(anyLong())).thenReturn(false);

        // When
        boolean result = customerService.deleteById(999L);

        // Then
        assertFalse(result);
        verify(repository).existsById(999L);
        verify(repository, never()).deleteById(anyLong());
    }
}