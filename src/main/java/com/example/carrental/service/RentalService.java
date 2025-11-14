package com.example.carrental.service;

import com.example.carrental.entity.Rental;
import com.example.carrental.entity.Car;
import com.example.carrental.entity.Customer;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    private final RentalRepository repo;
    private final CarRepository carRepo;
    private final CustomerRepository customerRepo;

    public RentalService(RentalRepository repo, CarRepository carRepo, CustomerRepository customerRepo){
        this.repo = repo; this.carRepo = carRepo; this.customerRepo = customerRepo;
    }

    public List<Rental> findAll(){ return repo.findAll(); }
    public Rental findById(Long id){ return repo.findById(id).orElse(null); }

    public Rental save(Rental r){
        if (r.getCar() != null && r.getCar().getId() != null){
            Car c = carRepo.findById(r.getCar().getId()).orElse(null);
            r.setCar(c);
            if (c != null) c.setStatus("Rented");
        }
        if (r.getCustomer() != null && r.getCustomer().getId() != null){
            Customer cu = customerRepo.findById(r.getCustomer().getId()).orElse(null);
            r.setCustomer(cu);
        }
        return repo.save(r);
    }
    public void delete(Long id){ repo.deleteById(id); }
}
