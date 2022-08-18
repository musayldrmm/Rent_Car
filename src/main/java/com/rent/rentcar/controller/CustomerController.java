package com.rent.rentcar.controller;

import com.rent.rentcar.auth.JwtTokenUtil;
import com.rent.rentcar.entity.AuthRequest;
import com.rent.rentcar.entity.AuthResponse;
import com.rent.rentcar.entity.Customer;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.LoginErrorMessage;
import com.rent.rentcar.exceptions.PostErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.CustomerRepository;
import com.rent.rentcar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private CustomerService customerservice;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/save")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws PostErrorMessage {
        String email = customer.getEmail();
        if (customerRepository.existsByEmail(email)) {
            throw new PostErrorMessage("Böyle bir e-mail değeri bulunmakta");
        }
        return ResponseEntity.ok(customerservice.addCustomer(customer));
    }

    @GetMapping("/get-token")
    public ResponseEntity<?> getToken() {
        return ResponseEntity.ok(jwtUtil.parseClaims("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1NTgsZGVuZW1lQG91dGxvb2suY29tIiwiaXNzIjoiUmVudC1jYXIiLCJpYXQiOjE2NjA3Mjc4NDcsImV4cCI6MTY2MDgxNDI0N30.t0mfUlawgfUj7JnNeABdNBPuDozKRuLKb7emqLlfnY3DAw-QGMgojg77QUknFZCn1R0qzeflRhRGyqUvfcNuLA"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            Customer customer = (Customer) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(customer);
            AuthResponse response = new AuthResponse("Bearer " + accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {

            throw new LoginErrorMessage("Şifreyi  hatalı girdiniz.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAllCustomer() {
        return ResponseEntity.ok(customerservice.findAllCustomer());
    }

    @GetMapping("/find-customer/{id}")
    public ResponseEntity<Customer> getProduct(@PathVariable Long id) throws GetByIdErrorMessage {
        if (!customerRepository.existsById(id)) {
            throw new GetByIdErrorMessage("Böyle bir kullanıcı kayıdı bulunmamakta.");
        }
        return ResponseEntity.ok(customerservice.getById(id));
    }

    @DeleteMapping("/remove-customer/{id}")
    public ResponseEntity<Customer> removeCustomer(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (!customerRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz kullanıcı kayıdı bulunamamaktadır");
        }
        customerservice.removeCustomer(id);
        return new ResponseEntity<Customer>(HttpStatus.ACCEPTED);
    }
}
