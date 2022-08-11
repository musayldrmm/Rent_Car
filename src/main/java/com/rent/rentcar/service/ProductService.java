package com.rent.rentcar.service;

import com.rent.rentcar.entity.Product;
import com.rent.rentcar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productrepository;

    public Product addProduct(Product product) {
        Date date = new Date();
        SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
        product.setCreatedDate(mdyFormat.format(date));
        return productrepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productrepository.findAll();
    }

    public Product getByIdProduct(Long productId) {
        return productrepository.findById(productId).get();
    }

    public void removeProduct(Long productId) {
        productrepository.deleteById(productId);
    }
}
