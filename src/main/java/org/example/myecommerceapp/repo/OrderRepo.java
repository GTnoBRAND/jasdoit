package org.example.myecommerceapp.repo;

import org.example.myecommerceapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getOrderByUser_id(Long userId);
}
