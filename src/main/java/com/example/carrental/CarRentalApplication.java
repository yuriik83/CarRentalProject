package com.example.carrental;

import com.example.carrental.entity.*;
import com.example.carrental.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class CarRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(CarModelRepository carModelRepo,
                               LocationRepository locationRepo,
                               CarRepository carRepo,
                               CustomerRepository customerRepo,
                               RentalRepository rentalRepo,
                               PaymentRepository paymentRepo) {
        return args -> {
            // Locations
            Location l1 = locationRepo.save(new Location(null, "Minsk", "Nemiga st 1"));
            Location l2 = locationRepo.save(new Location(null, "Grodno", "Center st 2"));

            // Car Models
            CarModel m1 = carModelRepo.save(new CarModel(null, "Toyota", "Corolla", "Petrol", 50.0));
            CarModel m2 = carModelRepo.save(new CarModel(null, "Tesla", "Model 3", "Electric", 120.0));

            // Cars
            Car c1 = carRepo.save(new Car(null, m1, l1, "1234-AB", "Available"));
            Car c2 = carRepo.save(new Car(null, m2, l2, "5555-AB", "Available"));

            // Customers
            Customer cust1 = customerRepo.save(new Customer(null, "Petrov Yury", "Yury@gmail.com", "+375292255566"));
            Customer cust2 = customerRepo.save(new Customer(null, "Avseev Anton", "Anton@gmail.com", "+375296458999"));

            // Rentals
            Rental r1 = rentalRepo.save(new Rental(null, c1, cust1,
                    LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(1), 100.0));
            Rental r2 = rentalRepo.save(new Rental(null, c2, cust2,
                    LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(2), 400.0));

            // Payments
            paymentRepo.save(new Payment(null, r1, 110.0, LocalDateTime.now().minusDays(1), "Card"));
            paymentRepo.save(new Payment(null, r2, 360.0, LocalDateTime.now().minusDays(2), "Cash"));
        };
    }
}
