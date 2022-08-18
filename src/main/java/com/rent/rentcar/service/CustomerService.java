package com.rent.rentcar.service;

import com.rent.rentcar.entity.Customer;
import com.rent.rentcar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private AuthenticationManager authenticationManager;

    public Customer addCustomer(Customer customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(customer.getPassword());
        System.out.println("password: " + password);
        customer.setPassword(password);
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getById(Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    public void removeCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
