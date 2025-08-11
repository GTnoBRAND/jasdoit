package org.example.myecommerceapp.rest;

import org.example.myecommerceapp.model.Order;
import org.example.myecommerceapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders{userId}")
    public ResponseEntity<List<Order>> getUsersByUserId(@PathVariable Long userId) {
        List<Order> orders = service.getOrdersByUsersId(userId);
        return ResponseEntity.ok(orders);
    }
}
