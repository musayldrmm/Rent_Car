package com.rent.rentcar.service;

import com.rent.rentcar.entity.Order;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order add(Order order) {
        Date date = new Date();
        SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
        order.setOrderDate(mdyFormat.format(date));

        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long orderId) throws GetByIdErrorMessage {
        return orderRepository.findById(orderId).get();
    }

    public void removeOrder(Long orderId) throws RemoveByIdErrorMessage {
        orderRepository.deleteById(orderId);
    }

}
