package com.example.carrental.service;

import com.example.carrental.entity.Location;
import com.example.carrental.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository repo;
    public LocationService(LocationRepository repo){ this.repo = repo; }
    public List<Location> findAll(){ return repo.findAll(); }
    public Location save(Location l){ return repo.save(l); }
    public Location findById(Long id){ return repo.findById(id).orElse(null); }
    public void delete(Long id){ repo.deleteById(id); }
}
