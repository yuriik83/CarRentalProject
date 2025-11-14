package com.example.carrental.service;

import com.example.carrental.entity.Customer;
import com.example.carrental.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo){ this.repo = repo; }
    public List<Customer> findAll(){ return repo.findAll(); }
    public Customer save(Customer c){ return repo.save(c); }
    public Customer findById(Long id){ return repo.findById(id).orElse(null); }
    public void delete(Long id){ repo.deleteById(id); }
}
