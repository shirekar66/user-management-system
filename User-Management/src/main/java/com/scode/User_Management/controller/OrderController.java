package com.scode.User_Management.controller;

import com.scode.User_Management.dto.CreateOrderDto;
import com.scode.User_Management.dto.OrderDto;
import com.scode.User_Management.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/{userId}/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId, @RequestBody CreateOrderDto createOrderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userId,createOrderDto));
    }

    /*@GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrder());
    }*/
    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderByUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByUser(userId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrderByUser(@PathVariable Long userId, @PathVariable Long orderId, @RequestBody CreateOrderDto updateOrderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderByUser(userId,orderId,updateOrderDto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long userId, @PathVariable Long orderId){
        orderService.deleteOrder(userId, orderId);
        return ResponseEntity.ok("Order deleted successfully for user Id: "+ userId + "with order Id" + orderId);
    }
}
