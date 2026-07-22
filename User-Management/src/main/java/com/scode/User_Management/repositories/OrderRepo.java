package com.scode.User_Management.repositories;

import com.scode.User_Management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Optional<Order> findByIdAndUserId(Long orderId,Long userId);

}
