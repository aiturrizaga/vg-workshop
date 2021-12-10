package com.foodfix.order.service;

import com.foodfix.order.document.Order;
import com.foodfix.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.insert(order);
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

}
