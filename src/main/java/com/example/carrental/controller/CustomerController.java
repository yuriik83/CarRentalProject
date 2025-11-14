package com.example.carrental.controller;

import com.example.carrental.entity.Customer;
import com.example.carrental.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service){ this.service = service; }

    @GetMapping
    public List<Customer> all(){ return service.findAll(); }

    @PostMapping
    public Customer create(@RequestBody Customer c){ return service.save(c); }
}
