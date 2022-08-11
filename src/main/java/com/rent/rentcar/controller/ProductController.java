package com.rent.rentcar.controller;

import com.rent.rentcar.entity.Product;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.ProductRepository;
import com.rent.rentcar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productservice;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/save")
    public ResponseEntity<Product> addCustomer(@RequestBody Product product) {
        return ResponseEntity.ok(productservice.addProduct(product));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllCustomer() {
        return ResponseEntity.ok(productservice.getAllProduct());
    }

    @GetMapping("/find-product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws GetByIdErrorMessage {
        if (productRepository.existsById(id)) {
            throw new GetByIdErrorMessage("Böyle bir ürün kayıdı bulunamamaktadır.");
        }
        return ResponseEntity.ok(productservice.getByIdProduct(id));
    }

    @DeleteMapping("/remove-product/{id}")
    public ResponseEntity<Product> removeCustomer(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (productRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz ürün kayıdı bulunamamakta");
        }
        productservice.removeProduct(id);
        return new ResponseEntity<Product>(HttpStatus.ACCEPTED);
    }
}
