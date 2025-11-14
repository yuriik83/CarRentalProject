package com.example.carrental.service;

import com.example.carrental.entity.Payment;
import com.example.carrental.entity.Rental;
import com.example.carrental.repository.PaymentRepository;
import com.example.carrental.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository repo;
    private final RentalRepository rentalRepo;
    public PaymentService(PaymentRepository repo, RentalRepository rentalRepo){ this.repo = repo; this.rentalRepo = rentalRepo; }
    public List<Payment> findAll(){ return repo.findAll(); }
    public Payment save(Payment p){
        if (p.getRental() != null && p.getRental().getId() != null){
            Rental r = rentalRepo.findById(p.getRental().getId()).orElse(null);
            p.setRental(r);
        }
        return repo.save(p);
    }
    public Payment findById(Long id){ return repo.findById(id).orElse(null); }
    public void delete(Long id){ repo.deleteById(id); }
}
