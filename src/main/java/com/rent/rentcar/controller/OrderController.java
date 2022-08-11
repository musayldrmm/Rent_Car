package com.rent.rentcar.controller;

import com.rent.rentcar.entity.Order;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.OrderRepository;
import com.rent.rentcar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/save")
    public ResponseEntity<Order> add(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.add(order));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/find-order/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) throws GetByIdErrorMessage {
        if (!orderRepository.existsById(id)) {
            throw new GetByIdErrorMessage("Sipariş kayıdı bulunamadı.");
        }
        return ResponseEntity.ok(orderService.getById(id));
    }

    @DeleteMapping("/remove-order/{id}")
    public ResponseEntity<Order> remove(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (!orderRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz kayıt bulunamadı");
        }
        orderService.removeOrder(id);
        return new ResponseEntity<Order>(HttpStatus.ACCEPTED);
    }
}
