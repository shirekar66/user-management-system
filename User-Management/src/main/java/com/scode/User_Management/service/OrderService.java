package com.scode.User_Management.service;

import com.scode.User_Management.dto.CreateOrderDto;
import com.scode.User_Management.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(Long userId, CreateOrderDto createOrderDto);
    List<OrderDto> getOrderByUser(Long userId);
    OrderDto updateOrderByUser(Long orderId,Long userId,CreateOrderDto updateOrderDto);
    void deleteOrder(Long orderId, Long userId);
}
