package com.example.carrental.controller;

import com.example.carrental.entity.Payment;
import com.example.carrental.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;
    public PaymentController(PaymentService service){ this.service = service; }

    @GetMapping
    public List<Payment> all(){ return service.findAll(); }

    @PostMapping
    public Payment create(@RequestBody Payment p){ return service.save(p); }
}
