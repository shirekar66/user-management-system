package com.scode.User_Management.controller;

import com.scode.User_Management.dto.CreateOrderDto;
import com.scode.User_Management.dto.OrderDto;
import com.scode.User_Management.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/{userId}/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId, @RequestBody CreateOrderDto createOrderDto) {
        return ResponseEntity.created(null).body(orderService.createOrder(userId, createOrderDto));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderByUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(orderService.getOrderByUser(userId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrderByUser(@PathVariable Long userId, @PathVariable Long orderId, @RequestBody CreateOrderDto updateOrderDto) {
        return ResponseEntity.ok().body(orderService.updateOrderByUser(userId, orderId, updateOrderDto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        orderService.deleteOrder(userId, orderId);
        return ResponseEntity.ok("Order deleted successfully for user Id: " + userId + "with order Id" + orderId);
    }
}
