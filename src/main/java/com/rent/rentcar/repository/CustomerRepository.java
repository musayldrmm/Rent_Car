package com.rent.rentcar.repository;

import com.rent.rentcar.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer>findByEmail(String email);



    boolean existsByEmail(String email);
}
