package com.scode.User_Management.service.impl;

import com.scode.User_Management.dto.CreateOrderDto;
import com.scode.User_Management.dto.OrderDto;
import com.scode.User_Management.entity.Order;
import com.scode.User_Management.entity.User;
import com.scode.User_Management.repositories.OrderRepo;
import com.scode.User_Management.repositories.UserRepo;
import com.scode.User_Management.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;


    public OrderDto createOrder(Long userId, CreateOrderDto createOrderDto) {
        User user = userRepo.findById(userId).orElseThrow();
        Order order = new Order();
        order.setUser(user);
        order.setProductName(createOrderDto.getProductName());

        Order saveOrder = orderRepo.save(order);
        return new OrderDto(saveOrder.getId(), saveOrder.getProductName(), saveOrder.getUser());
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getOrderByUser(Long userId) {
        return orderRepo.findByUserId(userId)
                .stream()
                .map(order -> new OrderDto(order.getId(), order.getProductName(), order.getUser()))
                .toList();
    }

    public OrderDto updateOrderByUser(Long userId, Long orderId, CreateOrderDto updateOrderDto) {
        Order order = orderRepo.findByIdAndUserId(orderId, userId).orElseThrow(() -> new RuntimeException("Order Not found user" + userId));
        order.setProductName(updateOrderDto.getProductName());
        return new OrderDto(order.getId(), order.getProductName(), order.getUser());
    }

    public void deleteOrder(Long userId, Long orderId) {
        Order order = orderRepo.findByIdAndUserId(orderId, userId).orElseThrow(() -> new RuntimeException("order id not found with id: " + orderId));
        orderRepo.delete(order);
    }
}
