package com.example.carrental.repository;

import com.example.carrental.entity.Customer;
import com.example.carrental.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void setUp() {
        customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john@example.com");
        customer1.setPhone("+1234567890");
        customer1.setRole(Role.USER);

        customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane@example.com");
        customer2.setPhone("+0987654321");
        customer2.setRole(Role.ADMIN);
    }

    @Test
    void save_ValidCustomer_ReturnsCustomerWithId() {
        // When
        Customer savedCustomer = customerRepository.save(customer1);

        // Then
        assertNotNull(savedCustomer.getId());
        assertEquals(customer1.getName(), savedCustomer.getName());
        assertEquals(customer1.getEmail(), savedCustomer.getEmail());
        assertEquals(customer1.getPhone(), savedCustomer.getPhone());
        assertEquals(customer1.getRole(), savedCustomer.getRole());
    }

    @Test
    void findById_ExistingCustomer_ReturnsCustomer() {
        // Given
        Customer savedCustomer = entityManager.persistAndFlush(customer1);

        // When
        Optional<Customer> found = customerRepository.findById(savedCustomer.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals(savedCustomer.getName(), found.get().getName());
        assertEquals(savedCustomer.getEmail(), found.get().getEmail());
    }

    @Test
    void findById_NonExistingCustomer_ReturnsEmpty() {
        // When
        Optional<Customer> found = customerRepository.findById(999L);

        // Then
        assertFalse(found.isPresent());
    }

    @Test
    void findAll_MultipleCustomers_ReturnsAllCustomers() {
        // Given
        entityManager.persistAndFlush(customer1);
        entityManager.persistAndFlush(customer2);

        // When
        List<Customer> customers = customerRepository.findAll();

        // Then
        assertEquals(2, customers.size());
        assertTrue(customers.stream().anyMatch(c -> c.getName().equals("John Doe")));
        assertTrue(customers.stream().anyMatch(c -> c.getName().equals("Jane Smith")));
    }

    @Test
    void findAll_WithPagination_ReturnsPagedResults() {
        // Given
        entityManager.persistAndFlush(customer1);
        entityManager.persistAndFlush(customer2);
        Pageable pageable = PageRequest.of(0, 1);

        // When
        Page<Customer> page = customerRepository.findAll(pageable);

        // Then
        assertEquals(1, page.getContent().size());
        assertEquals(2, page.getTotalElements());
        assertEquals(2, page.getTotalPages());
    }

    @Test
    void existsById_ExistingCustomer_ReturnsTrue() {
        // Given
        Customer savedCustomer = entityManager.persistAndFlush(customer1);

        // When
        boolean exists = customerRepository.existsById(savedCustomer.getId());

        // Then
        assertTrue(exists);
    }

    @Test
    void existsById_NonExistingCustomer_ReturnsFalse() {
        // When
        boolean exists = customerRepository.existsById(999L);

        // Then
        assertFalse(exists);
    }

    @Test
    void deleteById_ExistingCustomer_RemovesCustomer() {
        // Given
        Customer savedCustomer = entityManager.persistAndFlush(customer1);
        Long customerId = savedCustomer.getId();

        // When
        customerRepository.deleteById(customerId);
        entityManager.flush();

        // Then
        Optional<Customer> found = customerRepository.findById(customerId);
        assertFalse(found.isPresent());
    }

    @Test
    void count_MultipleCustomers_ReturnsCorrectCount() {
        // Given
        entityManager.persistAndFlush(customer1);
        entityManager.persistAndFlush(customer2);

        // When
        long count = customerRepository.count();

        // Then
        assertEquals(2, count);
    }
}