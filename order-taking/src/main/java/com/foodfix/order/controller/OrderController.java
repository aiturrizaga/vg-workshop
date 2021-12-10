package com.foodfix.order.controller;

import com.foodfix.order.document.Order;
import com.foodfix.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody Order order) {
        Order newOrder = orderService.save(order);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
