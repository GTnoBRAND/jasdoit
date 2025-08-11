package org.example.myecommerceapp.service;

import org.example.myecommerceapp.model.Order;
import org.example.myecommerceapp.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepo repo;
    @Autowired
    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public List<Order> getOrdersByUsersId(Long usersId) {
        return repo.getOrderByUser_id(usersId);
    }
}
